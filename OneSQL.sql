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



/****** Object:  Table [dbo].[SectionTest] ******/

CREATE TABLE [dbo].[SectionTest](
	[idSectionTest] [int] NOT NULL IDENTITY(1, 1) PRIMARY KEY,
	[idTest] [int] NOT NULL FOREIGN KEY REFERENCES Test(idTest),
	[idTheme] [int] NOT NULL FOREIGN KEY REFERENCES Theme(idTheme),
	[nbQuestionATirer] [int] NOT NULL 
) ON [PRIMARY]

GO



/************************************* DATA *********************************************/
USE [javaQCM]
GO
SET IDENTITY_INSERT [dbo].[Utilisateur] ON 

GO
INSERT [dbo].[Utilisateur] ([idUtilisateur], [nom], [prenom], [email], [password], [type]) VALUES (1, N'totoNom', N'totoPrenom', N'admin@campus-eni.fr', N'admin', N'admin')
GO
INSERT [dbo].[Utilisateur] ([idUtilisateur], [nom], [prenom], [email], [password], [type]) VALUES (2, N'adminNom', N'adminPrenom', N'toto@campus-eni.fr', N'toto', N'user')
GO
SET IDENTITY_INSERT [dbo].[Utilisateur] OFF
GO
SET IDENTITY_INSERT [dbo].[Test] ON 

GO
INSERT [dbo].[Test] ([idTest], [libelle], [description], [duree], [seuilHaut], [seuilBas]) VALUES (1, N'test1', N'le premier test', 60, CAST(20 AS Decimal(18, 0)), CAST(10 AS Decimal(18, 0)))
GO
INSERT [dbo].[Test] ([idTest], [libelle], [description], [duree], [seuilHaut], [seuilBas]) VALUES (2, N'test2', N'le second test', 120, CAST(30 AS Decimal(18, 0)), CAST(20 AS Decimal(18, 0)))
GO
INSERT [dbo].[Test] ([idTest], [libelle], [description], [duree], [seuilHaut], [seuilBas]) VALUES (3, N'test3', N'le troisième test', 360, CAST(2 AS Decimal(18, 0)), CAST(1 AS Decimal(18, 0)))
GO
SET IDENTITY_INSERT [dbo].[Test] OFF
GO
SET IDENTITY_INSERT [dbo].[Epreuve] ON 

GO
INSERT [dbo].[Epreuve] ([idEpreuve], [dateDebutValidite], [dateFinValidite], [tempsEcoule], [etat], [noteObtenu], [niveauObtenu], [idTest], [idUtilisateur]) VALUES (4, 999999, 999999, 50, 1, CAST(11 AS Decimal(18, 0)), 11, 1, 2)
GO
INSERT [dbo].[Epreuve] ([idEpreuve], [dateDebutValidite], [dateFinValidite], [tempsEcoule], [etat], [noteObtenu], [niveauObtenu], [idTest], [idUtilisateur]) VALUES (6, 999999, 999999, 60, 1, CAST(22 AS Decimal(18, 0)), 22, 2, 2)
GO
SET IDENTITY_INSERT [dbo].[Epreuve] OFF
GO
SET IDENTITY_INSERT [dbo].[Question] ON 

GO
INSERT [dbo].[Question] ([idQuestion], [enonce], [media], [points], [idTheme]) VALUES (1, N'premiere question', NULL, CAST(10 AS Decimal(18, 0)), 1)
GO
INSERT [dbo].[Question] ([idQuestion], [enonce], [media], [points], [idTheme]) VALUES (2, N'deuxieme question', NULL, CAST(20 AS Decimal(18, 0)), 2)
GO
SET IDENTITY_INSERT [dbo].[Question] OFF
GO
SET IDENTITY_INSERT [dbo].[Proposition] ON 

GO
INSERT [dbo].[Proposition] ([idProposition], [enonce], [estBonne], [idQuestion]) VALUES (1, N'reponse D', 1, 1)
GO
INSERT [dbo].[Proposition] ([idProposition], [enonce], [estBonne], [idQuestion]) VALUES (2, N'je sais pas', 0, 1)
GO
INSERT [dbo].[Proposition] ([idProposition], [enonce], [estBonne], [idQuestion]) VALUES (3, N'reponse C', 1, 1)
GO
INSERT [dbo].[Proposition] ([idProposition], [enonce], [estBonne], [idQuestion]) VALUES (5, N'1', 1, 2)
GO
INSERT [dbo].[Proposition] ([idProposition], [enonce], [estBonne], [idQuestion]) VALUES (7, N'2', 0, 2)
GO
SET IDENTITY_INSERT [dbo].[Proposition] OFF
GO
INSERT [dbo].[QuestionProposition] ([idQuestion], [idProposition]) VALUES (1, 1)
GO
INSERT [dbo].[QuestionProposition] ([idQuestion], [idProposition]) VALUES (1, 2)
GO
INSERT [dbo].[QuestionProposition] ([idQuestion], [idProposition]) VALUES (1, 3)
GO
INSERT [dbo].[QuestionProposition] ([idQuestion], [idProposition]) VALUES (2, 5)
GO
INSERT [dbo].[QuestionProposition] ([idQuestion], [idProposition]) VALUES (2, 7)
GO
SET IDENTITY_INSERT [dbo].[Theme] ON 

GO
INSERT [dbo].[Theme] ([idTheme], [libelle]) VALUES (1, N'Informatique')
GO
INSERT [dbo].[Theme] ([idTheme], [libelle]) VALUES (2, N'Economie')
GO
SET IDENTITY_INSERT [dbo].[Theme] OFF
GO


