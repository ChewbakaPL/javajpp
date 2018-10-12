USE [javaQCM]
GO

/****** Object:  Table [dbo].[Epreuve] ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[Epreuve](
	[idEpreuve] [int] NOT NULL IDENTITY(1, 1) PRIMARY KEY,
	[dateDebutValidite] [timestamp] NULL,
	[dateFinValidite] [timestamp] NULL,
	[tempsEcoule] [int] NULL,
	[etat] [int] NULL,
	[noteObtenu] [decimal](18, 0) NULL,
	[niveauObtenu] [int] NULL,
	[idTest] [int] NULL,
	[idQuestionTirages] [int] NULL
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

