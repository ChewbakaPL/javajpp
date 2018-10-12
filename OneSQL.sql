SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

USE [javaQCM]
GO

/****** Object:  Table [dbo].[Utilisateur]    Script Date: 12/10/2018 10:04:48 ******/

CREATE TABLE [dbo].[Utilisateur](
	[idUtilisateur] [int] NOT NULL IDENTITY(1, 1) PRIMARY KEY,
	[nom] [varchar](255) NULL,
	[prenom] [varchar](255) NULL,
	[email] [varchar](255) NULL,
	[password] [varchar](255) NULL,
	[type] [varchar](10) NULL
) ON [PRIMARY]

GO

/****** Object:  Table [dbo].[Question] ******/
CREATE TABLE [dbo].[Question](
	[idQuestion] [int] NOT NULL IDENTITY(1, 1) PRIMARY KEY,
	[enonce] [varchar](255) NULL,
	[media] [varchar](255) NULL,
	[points] [decimal](18, 0) NULL,
	[idTheme] [int] NULL,
) ON [PRIMARY]

GO

/****** Object:  Table [dbo].[Proposition] ******/

CREATE TABLE [dbo].[Proposition](
	[idProposition] [int] NOT NULL IDENTITY(1, 1) PRIMARY KEY,
	[enonce] [varchar](255) NULL,
	[estBonne] [tinyint] NOT NULL,
	[idQuestion] [int] NOT NULL FOREIGN KEY REFERENCES Question(idQuestion),
	
) ON [PRIMARY]

GO


/****** Object:  Table [dbo].[QuestionProposition] ******/

CREATE TABLE [dbo].[QuestionProposition](
	[idQuestion] [int] NOT NULL FOREIGN KEY REFERENCES Question(idQuestion),
	[idProposition] [int] NOT NULL FOREIGN KEY REFERENCES Proposition(idProposition),
) ON [PRIMARY]

GO

/****** Object:  Table [dbo].[Test]    Script Date: 11/10/2018 12:04:14 ******/

CREATE TABLE [dbo].[Test](
	[idTest] [int] NOT NULL IDENTITY(1, 1) PRIMARY KEY,
	[libelle] [varchar](255) NULL,
	[description] [varchar](255) NULL,
	[duree] [int] NULL,
	[seuilHaut] [decimal](18, 0) NULL,
	[seuilBas] [decimal](18, 0) NULL
) ON [PRIMARY]

GO


/****** Object:  Table [dbo].[Epreuve] ******/

CREATE TABLE [dbo].[Epreuve](
	[idEpreuve] [int] NOT NULL IDENTITY(1, 1) PRIMARY KEY,
	[dateDebutValidite] [bigint] NULL,
	[dateFinValidite] [bigint] NULL,
	[tempsEcoule] [int] NULL,
	[etat] [int] NULL,
	[noteObtenu] [decimal](18, 0) NULL,
	[niveauObtenu] [int] NULL,
	[idTest] [int] NOT NULL FOREIGN KEY REFERENCES Test(idTest),
	[idUtilisateur] [int] NOT NULL FOREIGN KEY REFERENCES Utilisateur(idUtilisateur)
) ON [PRIMARY]

GO

/****** Object:  Table [dbo].[QuestionTirage] ******/


CREATE TABLE [dbo].[QuestionTirage](
	[idEpreuve] [int] NOT NULL FOREIGN KEY REFERENCES Epreuve(idEpreuve),
	[idQuestion] [int] NOT NULL FOREIGN KEY REFERENCES Question(idQuestion),
	[estMarquee] [tinyint] NOT NULL,
	[numOrdre] [int] NOT NULL,
	
) ON [PRIMARY]

GO

/****** Object:  Table [dbo].[Utilisateur]    Script Date: 12/10/2018 10:04:48 ******/


CREATE TABLE [dbo].[Theme](
	[idTheme] [int] NOT NULL IDENTITY(1, 1) PRIMARY KEY,
	[libelle] [varchar](255) NULL
) ON [PRIMARY]

GO


SET ANSI_PADDING OFF

GO