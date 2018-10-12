USE [javaQCM]
GO

/****** Object:  Table [dbo].[Utilisateur]    Script Date: 12/10/2018 10:04:48 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[Utilisateur](
	[idUtilisateur] [int] NOT NULL IDENTITY(1, 1) PRIMARY KEY,
	[nom] [varchar](255) NULL,
	[prenom] [varchar](255) NULL,
	[email] [varchar](255) NULL,
	[password] [varchar](255) NULL,
	[type] [varchar](10) NULL
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

