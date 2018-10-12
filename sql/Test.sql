USE [javaQCM]
GO

/****** Object:  Table [dbo].[Test]    Script Date: 11/10/2018 12:04:14 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[Test](
	[idTest] [int] NULL,
	[libelle] [varchar](255) NULL,
	[description] [varchar](255) NULL,
	[duree] [int] NULL,
	[seuilHaut] [decimal](18, 0) NULL,
	[seuilBas] [decimal](18, 0) NULL
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

