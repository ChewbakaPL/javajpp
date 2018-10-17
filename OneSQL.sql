USE [master]
GO
/****** Object:  Database [javaQCM]    Script Date: 17/10/2018 11:56:39 ******/
CREATE DATABASE [javaQCM]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'javaQCM', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\javaQCM.mdf' , SIZE = 5120KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'javaQCM_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\javaQCM_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [javaQCM] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [javaQCM].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [javaQCM] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [javaQCM] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [javaQCM] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [javaQCM] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [javaQCM] SET ARITHABORT OFF 
GO
ALTER DATABASE [javaQCM] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [javaQCM] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [javaQCM] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [javaQCM] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [javaQCM] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [javaQCM] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [javaQCM] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [javaQCM] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [javaQCM] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [javaQCM] SET  DISABLE_BROKER 
GO
ALTER DATABASE [javaQCM] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [javaQCM] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [javaQCM] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [javaQCM] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [javaQCM] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [javaQCM] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [javaQCM] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [javaQCM] SET RECOVERY FULL 
GO
ALTER DATABASE [javaQCM] SET  MULTI_USER 
GO
ALTER DATABASE [javaQCM] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [javaQCM] SET DB_CHAINING OFF 
GO
ALTER DATABASE [javaQCM] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [javaQCM] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [javaQCM] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'javaQCM', N'ON'
GO
USE [javaQCM]
GO
/****** Object:  Table [dbo].[Epreuve]    Script Date: 17/10/2018 11:56:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Epreuve](
	[idEpreuve] [int] IDENTITY(1,1) NOT NULL,
	[dateDebutValidite] [bigint] NULL,
	[dateFinValidite] [bigint] NULL,
	[tempsEcoule] [int] NULL,
	[etat] [int] NULL,
	[noteObtenu] [decimal](18, 0) NULL,
	[niveauObtenu] [int] NULL,
	[idTest] [int] NOT NULL,
	[idUtilisateur] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[idEpreuve] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Proposition]    Script Date: 17/10/2018 11:56:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Proposition](
	[idProposition] [int] IDENTITY(1,1) NOT NULL,
	[enonce] [varchar](255) NULL,
	[estBonne] [tinyint] NOT NULL,
	[idQuestion] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[idProposition] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Question]    Script Date: 17/10/2018 11:56:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Question](
	[idQuestion] [int] IDENTITY(1,1) NOT NULL,
	[enonce] [varchar](255) NULL,
	[media] [varchar](255) NULL,
	[points] [decimal](18, 0) NULL,
	[idTheme] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[idQuestion] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[QuestionProposition]    Script Date: 17/10/2018 11:56:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[QuestionProposition](
	[idQuestion] [int] NOT NULL,
	[idProposition] [int] NOT NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[QuestionTirage]    Script Date: 17/10/2018 11:56:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[QuestionTirage](
	[idQuestionTirage] [int] IDENTITY(1,1) NOT NULL,
	[idEpreuve] [int] NOT NULL,
	[idQuestion] [int] NOT NULL,
	[estMarquee] [tinyint] NOT NULL,
	[numOrdre] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[idQuestionTirage] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ReponseUtilisateur]    Script Date: 17/10/2018 11:56:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ReponseUtilisateur](
	[idQuestionTirage] [int] NOT NULL,
	[idProposition] [int] NOT NULL,
	[idQuestion] [int] NOT NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[SectionTest]    Script Date: 17/10/2018 11:56:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SectionTest](
	[idSectionTest] [int] IDENTITY(1,1) NOT NULL,
	[idTest] [int] NOT NULL,
	[idTheme] [int] NOT NULL,
	[nbQuestionATirer] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[idSectionTest] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Test]    Script Date: 17/10/2018 11:56:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Test](
	[idTest] [int] IDENTITY(1,1) NOT NULL,
	[libelle] [varchar](255) NULL,
	[description] [varchar](255) NULL,
	[duree] [int] NULL,
	[seuilHaut] [decimal](18, 0) NULL,
	[seuilBas] [decimal](18, 0) NULL,
PRIMARY KEY CLUSTERED 
(
	[idTest] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Theme]    Script Date: 17/10/2018 11:56:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Theme](
	[idTheme] [int] IDENTITY(1,1) NOT NULL,
	[libelle] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[idTheme] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Utilisateur]    Script Date: 17/10/2018 11:56:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Utilisateur](
	[idUtilisateur] [int] IDENTITY(1,1) NOT NULL,
	[nom] [varchar](255) NULL,
	[prenom] [varchar](255) NULL,
	[email] [varchar](255) NULL,
	[password] [varchar](255) NULL,
	[type] [varchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[idUtilisateur] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[Epreuve] ON 

GO
INSERT [dbo].[Epreuve] ([idEpreuve], [dateDebutValidite], [dateFinValidite], [tempsEcoule], [etat], [noteObtenu], [niveauObtenu], [idTest], [idUtilisateur]) VALUES (4, 999999, 999999, 50, 1, CAST(11 AS Decimal(18, 0)), 11, 1, 2)
GO
INSERT [dbo].[Epreuve] ([idEpreuve], [dateDebutValidite], [dateFinValidite], [tempsEcoule], [etat], [noteObtenu], [niveauObtenu], [idTest], [idUtilisateur]) VALUES (6, 999999, 999999, 60, 1, CAST(22 AS Decimal(18, 0)), 22, 2, 2)
GO
SET IDENTITY_INSERT [dbo].[Epreuve] OFF
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
SET IDENTITY_INSERT [dbo].[Question] ON 

GO
INSERT [dbo].[Question] ([idQuestion], [enonce], [media], [points], [idTheme]) VALUES (1, N'premiere question', NULL, CAST(10 AS Decimal(18, 0)), 1)
GO
INSERT [dbo].[Question] ([idQuestion], [enonce], [media], [points], [idTheme]) VALUES (2, N'deuxieme question', NULL, CAST(20 AS Decimal(18, 0)), 1)
GO
SET IDENTITY_INSERT [dbo].[Question] OFF
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
SET IDENTITY_INSERT [dbo].[QuestionTirage] ON 

GO
INSERT [dbo].[QuestionTirage] ([idQuestionTirage], [idEpreuve], [idQuestion], [estMarquee], [numOrdre]) VALUES (1, 4, 1, 0, 1)
GO
INSERT [dbo].[QuestionTirage] ([idQuestionTirage], [idEpreuve], [idQuestion], [estMarquee], [numOrdre]) VALUES (2, 4, 2, 1, 2)
GO
SET IDENTITY_INSERT [dbo].[QuestionTirage] OFF
GO
INSERT [dbo].[ReponseUtilisateur] ([idQuestionTirage], [idProposition], [idQuestion]) VALUES (1, 1, 1)
GO
INSERT [dbo].[ReponseUtilisateur] ([idQuestionTirage], [idProposition], [idQuestion]) VALUES (1, 3, 1)
GO
SET IDENTITY_INSERT [dbo].[SectionTest] ON 

GO
INSERT [dbo].[SectionTest] ([idSectionTest], [idTest], [idTheme], [nbQuestionATirer]) VALUES (1, 1, 1, 2)
GO
INSERT [dbo].[SectionTest] ([idSectionTest], [idTest], [idTheme], [nbQuestionATirer]) VALUES (2, 2, 2, 4)
GO
SET IDENTITY_INSERT [dbo].[SectionTest] OFF
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
SET IDENTITY_INSERT [dbo].[Theme] ON 

GO
INSERT [dbo].[Theme] ([idTheme], [libelle]) VALUES (1, N'Informatique')
GO
INSERT [dbo].[Theme] ([idTheme], [libelle]) VALUES (2, N'Economie')
GO
SET IDENTITY_INSERT [dbo].[Theme] OFF
GO
SET IDENTITY_INSERT [dbo].[Utilisateur] ON 

GO
INSERT [dbo].[Utilisateur] ([idUtilisateur], [nom], [prenom], [email], [password], [type]) VALUES (1, N'totoNom', N'totoPrenom', N'admin@campus-eni.fr', N'admin', N'admin')
GO
INSERT [dbo].[Utilisateur] ([idUtilisateur], [nom], [prenom], [email], [password], [type]) VALUES (2, N'adminNom', N'adminPrenom', N'toto@campus-eni.fr', N'toto', N'user')
GO
SET IDENTITY_INSERT [dbo].[Utilisateur] OFF
GO
ALTER TABLE [dbo].[Epreuve]  WITH CHECK ADD FOREIGN KEY([idTest])
REFERENCES [dbo].[Test] ([idTest])
GO
ALTER TABLE [dbo].[Epreuve]  WITH CHECK ADD FOREIGN KEY([idUtilisateur])
REFERENCES [dbo].[Utilisateur] ([idUtilisateur])
GO
ALTER TABLE [dbo].[Proposition]  WITH CHECK ADD FOREIGN KEY([idQuestion])
REFERENCES [dbo].[Question] ([idQuestion])
GO
ALTER TABLE [dbo].[QuestionProposition]  WITH CHECK ADD FOREIGN KEY([idProposition])
REFERENCES [dbo].[Proposition] ([idProposition])
GO
ALTER TABLE [dbo].[QuestionProposition]  WITH CHECK ADD FOREIGN KEY([idQuestion])
REFERENCES [dbo].[Question] ([idQuestion])
GO
ALTER TABLE [dbo].[QuestionTirage]  WITH CHECK ADD FOREIGN KEY([idEpreuve])
REFERENCES [dbo].[Epreuve] ([idEpreuve])
GO
ALTER TABLE [dbo].[QuestionTirage]  WITH CHECK ADD FOREIGN KEY([idQuestion])
REFERENCES [dbo].[Question] ([idQuestion])
GO
ALTER TABLE [dbo].[ReponseUtilisateur]  WITH CHECK ADD FOREIGN KEY([idProposition])
REFERENCES [dbo].[Proposition] ([idProposition])
GO
ALTER TABLE [dbo].[ReponseUtilisateur]  WITH CHECK ADD FOREIGN KEY([idQuestionTirage])
REFERENCES [dbo].[QuestionTirage] ([idQuestionTirage])
GO
ALTER TABLE [dbo].[ReponseUtilisateur]  WITH CHECK ADD FOREIGN KEY([idQuestion])
REFERENCES [dbo].[Question] ([idQuestion])
GO
ALTER TABLE [dbo].[SectionTest]  WITH CHECK ADD FOREIGN KEY([idTest])
REFERENCES [dbo].[Test] ([idTest])
GO
ALTER TABLE [dbo].[SectionTest]  WITH CHECK ADD FOREIGN KEY([idTheme])
REFERENCES [dbo].[Theme] ([idTheme])
GO
USE [master]
GO
ALTER DATABASE [javaQCM] SET  READ_WRITE 
GO
