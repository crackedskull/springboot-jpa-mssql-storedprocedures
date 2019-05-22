USE master
GO

IF EXISTS
(
    SELECT
        1
    FROM
        sys.databases
    WHERE
        name = 'JPA'
)
    BEGIN
        ALTER DATABASE JPA
            SET
                SINGLE_USER
            WITH ROLLBACK IMMEDIATE
    END
GO

IF EXISTS
(
    SELECT
        1
    FROM
        sys.databases
    WHERE
        name = 'JPA'
)
    BEGIN
        DROP DATABASE JPA
    END
GO

CREATE DATABASE JPA
GO

USE JPA
GO

CREATE TABLE dbo.Customers (
    Id        INT           IDENTITY(1, 1) NOT NULL,
    FirstName NVARCHAR(255) NOT NULL,
    LastName  NVARCHAR(255) NOT NULL,
    CONSTRAINT PK_Customers
        PRIMARY KEY CLUSTERED
        (
            Id ASC
        )
)
GO

CREATE PROCEDURE dbo.AddCustomer
    @id        INT OUTPUT,
    @firstName NVARCHAR(255),
    @lastName  NVARCHAR(255)
AS
    SET XACT_ABORT, NOCOUNT ON

    BEGIN TRY
        DECLARE @nested INT = @@TRANCOUNT

        IF @nested = 0
            BEGIN TRANSACTION

        INSERT INTO dbo.Customers (
            FirstName,
            LastName
        )
        VALUES
            (@firstName, @lastName)

        IF @nested = 0
            BEGIN
                IF XACT_STATE() = 1
                    BEGIN
                        COMMIT TRANSACTION
                    END
                ELSE
                    BEGIN
                        RAISERROR(N'Unable to commit transaction!', 16, 1)
                    END
            END

        SELECT
            @id = SCOPE_IDENTITY()
    END TRY
    BEGIN CATCH
        IF @nested = 0
            AND XACT_STATE() <> 0
            BEGIN
                ROLLBACK TRANSACTION
            END;

        THROW
    END CATCH
GO

CREATE PROCEDURE dbo.RemoveCustomer
    @id INT
AS
    SET XACT_ABORT, NOCOUNT ON

    BEGIN TRY
        DECLARE @nested INT = @@TRANCOUNT

        IF @nested = 0
            BEGIN TRANSACTION

        DELETE FROM
        dbo.Customers
        WHERE
            Id = @id

        IF @nested = 0
            BEGIN
                IF XACT_STATE() = 1
                    BEGIN
                        COMMIT TRANSACTION
                    END
                ELSE
                    BEGIN
                        RAISERROR(N'Unable to commit transaction!', 16, 1)
                    END
            END
    END TRY
    BEGIN CATCH
        IF @nested = 0
            AND XACT_STATE() <> 0
            BEGIN
                ROLLBACK TRANSACTION
            END;

        THROW
    END CATCH
GO