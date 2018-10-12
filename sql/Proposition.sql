USE [javaQCM]
GO

/****** Object:  Table [dbo].[Proposition] ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[Proposition](
	[idProposition] [int] NOT NULL IDENTITY(1, 1) PRIMARY KEY,
	[enonce] [varchar](255) NULL,
	[estBonne] [tinyint] NOT NULL,
	[idQuestion] [int] NOT NULL FOREIGN KEY REFERENCES Question(idQuestion),
	
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

