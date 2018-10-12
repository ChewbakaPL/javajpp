USE [javaQCM]
GO

/****** Object:  Table [dbo].[Question] ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[Question](
	[idQuestion] [int] NOT NULL IDENTITY(1, 1) PRIMARY KEY,
	[enonce] [varchar](255) NULL,
	[media] [varchar](255) NULL,
	[points] [decimal](18, 0) NULL,
	[idTheme] [int] NULL,
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

