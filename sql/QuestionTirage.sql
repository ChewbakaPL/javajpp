USE [javaQCM]
GO

/****** Object:  Table [dbo].[QuestionTirage] ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[QuestionTirage](
	[idEpreuve] [int] NOT NULL FOREIGN KEY REFERENCES Epreuve(idEpreuve),
	[idQuestion] [int] NOT NULL FOREIGN KEY REFERENCES Question(idQuestion),
	[estMarquee] [tinyint] NOT NULL,
	[numOrdre] [int] NOT NULL,
	
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

