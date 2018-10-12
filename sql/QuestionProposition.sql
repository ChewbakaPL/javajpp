USE [javaQCM]
GO

/****** Object:  Table [dbo].[QuestionProposition] ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[QuestionProposition](
	[idQuestion] [int] NOT NULL FOREIGN KEY REFERENCES Question(idQuestion),
	[idProposition] [int] NOT NULL FOREIGN KEY REFERENCES Proposition(idProposition),
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

