USE [ECourse]
GO
/****** Object:  Table [dbo].[Answer]    Script Date: 5/30/2024 9:33:12 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Answer](
	[QuestionID] [int] NOT NULL,
	[AnswerID] [int] NOT NULL,
	[Description] [nvarchar](100) NOT NULL,
	[Role] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[AnswerID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Cart]    Script Date: 5/30/2024 9:33:12 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cart](
	[SessionID] [int] NOT NULL,
	[LessonID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[SessionID] ASC,
	[LessonID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Discount]    Script Date: 5/30/2024 9:33:12 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Discount](
	[DiscountID] [int] NOT NULL,
	[Percentage] [float] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[DiscountID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Feedback]    Script Date: 5/30/2024 9:33:12 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Feedback](
	[LessonID] [int] NOT NULL,
	[FeedbackID] [int] NOT NULL,
	[Title] [nvarchar](100) NOT NULL,
	[Description] [nvarchar](100) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[FeedbackID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Lesson]    Script Date: 5/30/2024 9:33:12 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Lesson](
	[LessonID] [int] NOT NULL,
	[LessonName] [nvarchar](100) NOT NULL,
	[Price] [float] NOT NULL,
	[DiscountID] [int] NOT NULL,
	[Description] [nvarchar](500) NULL,
	[CreateDate] [date] NOT NULL,
	[TagLine] [nvarchar](max) NOT NULL,
	[Title] [nvarchar](max) NOT NULL,
	[Image] [nvarchar](max) NOT NULL,
 CONSTRAINT [PK__Lesson__B084ACB05252AEEA] PRIMARY KEY CLUSTERED 
(
	[LessonID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Order]    Script Date: 5/30/2024 9:33:12 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order](
	[OrderID] [int] NOT NULL,
	[UserID] [int] NOT NULL,
	[Status] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[OrderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderLesson]    Script Date: 5/30/2024 9:33:12 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderLesson](
	[OrderID] [int] NOT NULL,
	[LessonID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[OrderID] ASC,
	[LessonID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Question]    Script Date: 5/30/2024 9:33:12 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Question](
	[QuizID] [int] NOT NULL,
	[QuestionID] [int] NOT NULL,
	[Question] [nvarchar](100) NOT NULL,
	[Explaination] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[QuestionID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[QuestionStatus]    Script Date: 5/30/2024 9:33:12 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[QuestionStatus](
	[QuestionID] [int] NOT NULL,
	[UserID] [int] NOT NULL,
	[Status] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[QuestionID] ASC,
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Quiz]    Script Date: 5/30/2024 9:33:12 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Quiz](
	[QuizID] [int] NOT NULL,
	[LessonID] [int] NOT NULL,
	[QuizName] [nvarchar](100) NOT NULL,
	[CreateDate] [date] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[QuizID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[QuizStatus]    Script Date: 5/30/2024 9:33:12 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[QuizStatus](
	[UserID] [int] NOT NULL,
	[QuizID] [int] NOT NULL,
	[Status] [int] NOT NULL,
	[Mark] [float] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SEQuestion]    Script Date: 5/30/2024 9:33:12 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SEQuestion](
	[SecurityQuestionID] [int] NOT NULL,
	[Question] [nvarchar](100) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[SecurityQuestionID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Session]    Script Date: 5/30/2024 9:33:12 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Session](
	[UserID] [int] NOT NULL,
	[SessionID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[SessionID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User]    Script Date: 5/30/2024 9:33:12 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[UserID] [int] NOT NULL,
	[UserName] [nvarchar](100) NOT NULL,
	[Password] [nvarchar](100) NOT NULL,
	[Mail] [nvarchar](100) NOT NULL,
	[FullName] [nvarchar](100) NOT NULL,
	[DoB] [date] NOT NULL,
	[SecurityQuestionID] [int] NOT NULL,
	[Answer] [nvarchar](100) NOT NULL,
	[Role] [int] NOT NULL,
	[Status] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Discount] ([DiscountID], [Percentage]) VALUES (1, 10)
GO
INSERT [dbo].[Lesson] ([LessonID], [LessonName], [Price], [DiscountID], [Description], [CreateDate], [TagLine], [Title], [Image]) VALUES (1, N'.NET', 180000, 1, N'Math for Beginer', CAST(N'2024-05-23' AS Date), N'C#', N'Math 1', N'https://wakeupandcode.com/wp-content/uploads/2019/03/C.NET_-1024x384-1.png')
INSERT [dbo].[Lesson] ([LessonID], [LessonName], [Price], [DiscountID], [Description], [CreateDate], [TagLine], [Title], [Image]) VALUES (2, N'Java', 200000, 1, N'Java Core', CAST(N'2024-05-06' AS Date), N'Java cơ bane', N'Java Basic', N'https://th.bing.com/th?id=OSAAS.D8CE0A36BF5600CBE0F94BA7702AFA72&w=80&h=80&c=17&rs=1&o=6&dpr=1.1&pid=5.1')
GO
INSERT [dbo].[Order] ([OrderID], [UserID], [Status]) VALUES (1, 1, 0)
GO
INSERT [dbo].[OrderLesson] ([OrderID], [LessonID]) VALUES (1, 1)
INSERT [dbo].[OrderLesson] ([OrderID], [LessonID]) VALUES (1, 2)
GO
INSERT [dbo].[SEQuestion] ([SecurityQuestionID], [Question]) VALUES (1, N'Bạn thích ăn gì ?')
INSERT [dbo].[SEQuestion] ([SecurityQuestionID], [Question]) VALUES (2, N'Bố bạn tên gì?')
INSERT [dbo].[SEQuestion] ([SecurityQuestionID], [Question]) VALUES (3, N'Mẹ bạn tên gì?')
INSERT [dbo].[SEQuestion] ([SecurityQuestionID], [Question]) VALUES (4, N'Anh bạn có người yêu tên gì ?')
GO
INSERT [dbo].[User] ([UserID], [UserName], [Password], [Mail], [FullName], [DoB], [SecurityQuestionID], [Answer], [Role], [Status]) VALUES (1, N'sa', N'12', N'sa@gmail.com', N'nguyen van sa', CAST(N'2001-01-01' AS Date), 1, N'rau', 1, 0)
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__User__2724B2D1A04511E8]    Script Date: 5/30/2024 9:33:12 AM ******/
ALTER TABLE [dbo].[User] ADD UNIQUE NONCLUSTERED 
(
	[Mail] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__User__C9F284567A0AF4CF]    Script Date: 5/30/2024 9:33:12 AM ******/
ALTER TABLE [dbo].[User] ADD UNIQUE NONCLUSTERED 
(
	[UserName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Answer]  WITH NOCHECK ADD FOREIGN KEY([QuestionID])
REFERENCES [dbo].[Question] ([QuestionID])
GO
ALTER TABLE [dbo].[Cart]  WITH NOCHECK ADD  CONSTRAINT [FK__Cart__LessonID__44FF419A] FOREIGN KEY([LessonID])
REFERENCES [dbo].[Lesson] ([LessonID])
GO
ALTER TABLE [dbo].[Cart] CHECK CONSTRAINT [FK__Cart__LessonID__44FF419A]
GO
ALTER TABLE [dbo].[Cart]  WITH NOCHECK ADD FOREIGN KEY([SessionID])
REFERENCES [dbo].[Session] ([SessionID])
GO
ALTER TABLE [dbo].[Feedback]  WITH NOCHECK ADD  CONSTRAINT [FK__Feedback__Lesson__440B1D61] FOREIGN KEY([LessonID])
REFERENCES [dbo].[Lesson] ([LessonID])
GO
ALTER TABLE [dbo].[Feedback] CHECK CONSTRAINT [FK__Feedback__Lesson__440B1D61]
GO
ALTER TABLE [dbo].[Lesson]  WITH NOCHECK ADD  CONSTRAINT [FK__Lesson__Discount__4222D4EF] FOREIGN KEY([DiscountID])
REFERENCES [dbo].[Discount] ([DiscountID])
GO
ALTER TABLE [dbo].[Lesson] CHECK CONSTRAINT [FK__Lesson__Discount__4222D4EF]
GO
ALTER TABLE [dbo].[Order]  WITH NOCHECK ADD FOREIGN KEY([UserID])
REFERENCES [dbo].[User] ([UserID])
GO
ALTER TABLE [dbo].[OrderLesson]  WITH NOCHECK ADD  CONSTRAINT [FK__OrderLess__Lesso__45F365D3] FOREIGN KEY([LessonID])
REFERENCES [dbo].[Lesson] ([LessonID])
GO
ALTER TABLE [dbo].[OrderLesson] CHECK CONSTRAINT [FK__OrderLess__Lesso__45F365D3]
GO
ALTER TABLE [dbo].[OrderLesson]  WITH NOCHECK ADD FOREIGN KEY([OrderID])
REFERENCES [dbo].[Order] ([OrderID])
GO
ALTER TABLE [dbo].[Question]  WITH NOCHECK ADD FOREIGN KEY([QuizID])
REFERENCES [dbo].[Quiz] ([QuizID])
GO
ALTER TABLE [dbo].[QuestionStatus]  WITH NOCHECK ADD FOREIGN KEY([QuestionID])
REFERENCES [dbo].[Question] ([QuestionID])
GO
ALTER TABLE [dbo].[QuestionStatus]  WITH NOCHECK ADD FOREIGN KEY([UserID])
REFERENCES [dbo].[User] ([UserID])
GO
ALTER TABLE [dbo].[Quiz]  WITH NOCHECK ADD  CONSTRAINT [FK__Quiz__LessonID__46E78A0C] FOREIGN KEY([LessonID])
REFERENCES [dbo].[Lesson] ([LessonID])
GO
ALTER TABLE [dbo].[Quiz] CHECK CONSTRAINT [FK__Quiz__LessonID__46E78A0C]
GO
ALTER TABLE [dbo].[QuizStatus]  WITH NOCHECK ADD FOREIGN KEY([QuizID])
REFERENCES [dbo].[Quiz] ([QuizID])
GO
ALTER TABLE [dbo].[QuizStatus]  WITH NOCHECK ADD FOREIGN KEY([UserID])
REFERENCES [dbo].[User] ([UserID])
GO
ALTER TABLE [dbo].[Session]  WITH NOCHECK ADD FOREIGN KEY([UserID])
REFERENCES [dbo].[User] ([UserID])
GO
ALTER TABLE [dbo].[User]  WITH NOCHECK ADD FOREIGN KEY([SecurityQuestionID])
REFERENCES [dbo].[SEQuestion] ([SecurityQuestionID])
GO
