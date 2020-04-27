USE master;
GO

IF DB_ID(N'JPA') IS NULL
BEGIN
    CREATE DATABASE JPA;
END;
GO

USE JPA;
GO

DROP TABLE IF EXISTS dbo.Customers;
GO

CREATE TABLE dbo.Customers (
    Id          INT           IDENTITY(1, 1) NOT NULL
  , FirstName   NVARCHAR(255) NOT NULL
  , LastName    NVARCHAR(255) NOT NULL
  , DateOfBirth DATE          NOT NULL
  , CONSTRAINT PK_Customers PRIMARY KEY CLUSTERED (Id ASC) WITH (FILLFACTOR = 95, PAD_INDEX = OFF)
);
GO

CREATE OR ALTER PROCEDURE dbo.AddCustomer
    @id          INT OUTPUT
  , @firstName   NVARCHAR(255)
  , @lastName    NVARCHAR(255)
  , @dateOfBirth DATE
AS
BEGIN
    SET XACT_ABORT, NOCOUNT ON;

    BEGIN TRY
        DECLARE @nested INT = @@TRANCOUNT;

        IF @nested = 0
        BEGIN
            BEGIN TRANSACTION;
        END;

        INSERT INTO dbo.Customers (
            FirstName
          , LastName
          , DateOfBirth
        )
        VALUES
             (
                 @firstName
               , @lastName
               , @dateOfBirth
             );

        SELECT
            @id = SCOPE_IDENTITY();

        IF @nested = 0
        BEGIN
            IF XACT_STATE() = 1
            BEGIN
                COMMIT TRANSACTION;
            END;
            ELSE
            BEGIN
                RAISERROR(N'Unable to commit transaction!', 16, 1);
            END;
        END;
    END TRY
    BEGIN CATCH
        IF @nested = 0
           AND XACT_STATE() <> 0
        BEGIN
            ROLLBACK TRANSACTION;
        END;

        THROW;
    END CATCH;
END;
GO

CREATE OR ALTER PROCEDURE dbo.RemoveCustomer
    @id INT
AS
BEGIN
    SET XACT_ABORT, NOCOUNT ON;

    BEGIN TRY
        DECLARE @nested INT = @@TRANCOUNT;

        IF @nested = 0
            BEGIN TRANSACTION;

        DELETE FROM
        dbo.Customers
        WHERE
            Id = @id;

        IF @nested = 0
        BEGIN
            IF XACT_STATE() = 1
            BEGIN
                COMMIT TRANSACTION;
            END;
            ELSE
            BEGIN
                RAISERROR(N'Unable to commit transaction!', 16, 1);
            END;
        END;
    END TRY
    BEGIN CATCH
        IF @nested = 0
           AND XACT_STATE() <> 0
        BEGIN
            ROLLBACK TRANSACTION;
        END;

        THROW;
    END CATCH;
END;
GO

CREATE OR ALTER PROCEDURE dbo.GetDemographics
AS
BEGIN
    SET XACT_ABORT, NOCOUNT ON;

    DECLARE @today DATE = GETDATE();

    SELECT
        Age = DATEDIFF(YEAR, DateOfBirth, @today)
      , Count = COUNT(*)
    FROM
        dbo.Customers
    GROUP BY
        DateOfBirth
    ORDER BY
        Age ASC;
END;
GO
/*
DECLARE
    @i          INT = 0
  , @iterations INT = 10;

WHILE @i < @iterations
BEGIN
    SET @i += 1;

    INSERT INTO dbo.Customers (
        FirstName
      , LastName
      , DateOfBirth
    )
    VALUES
         (
             NEWID()
           , NEWID()
           , DATEADD(DAY, (ABS(CHECKSUM(NEWID())) % 65530), 0)
         );
END;
GO

SELECT
    *
FROM
    dbo.Customers;
GO

EXEC dbo.GetDemographics;
GO
*/