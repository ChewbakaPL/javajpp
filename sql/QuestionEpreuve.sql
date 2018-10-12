USE [javaQCM]
GO

/****** Object:  Table [dbo].[QuestionEpreuve] ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[QuestionEpreuve](
	[idQuestion] [int] NOT NULL FOREIGN KEY REFERENCES Question(idQuestion),
	[idEpreuve] [int] NOT NULL FOREIGN KEY REFERENCES Epreuve(idEpreuve),
	
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

