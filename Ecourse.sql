Create Database ECourse
Go

Use ECourse

Create Table [User](
	UserID int not null,
	UserName nvarchar(100) not null unique,
	[Password] nvarchar(100) not null, 
	Mail nvarchar(100) not null unique,
	FullName nvarchar(100) not null,
	DoB Date not null,	
	SecurityQuestionID int not null,
	Answer nvarchar(100) not null,
	[Role] int not null,
	[Status] int not null,
	Primary Key(UserID)	
);

Create Table [SEQuestion](
	SecurityQuestionID int not null,
	Question nvarchar(100) not null,
	Primary Key (SecurityQuestionID)
);

Create Table [Lesson](
	LessonID int not null,
	LessonName nvarchar(100) not null,
	Price float not null,	
	[Description] nvarchar(500),
	CreateDate date not null,
	Primary Key(LessonID)
);

Create Table [Discount](
	LessonID int not null,
	[Percentage] float not null,
	Primary Key (LessonID)
);

Create Table [Feedback](
	LessonID int not null,
	FeedbackID int not null,	
	Title nvarchar(100) not null,
	[Description] nvarchar(100) not null,
	Primary Key (FeedbackID)
);

Create Table [Session](
	UserID int not null,
	SessionID int not null,	
	Primary Key (SessionID)
);

Create Table Cart(
	SessionID int not null,
	LessonID int not null,	
	Primary Key (SessionID, LessonID)
);

Create Table [Order](
	OrderID int not null,
	UserID int not null,
	[Status] int not null,
	Primary Key (OrderID)
);

Create Table [OrderLesson](
	OrderID int not null,
	LessonID int not null,		
	Primary Key (OrderID, LessonID)
);

Create Table [Quiz](
	QuizID int not null,
	LessonID int not null,
	QuizName nvarchar(100) not null,
	CreateDate date not null,
	Primary Key (QuizID)
);

Create Table [Question](
	QuizID int not null,
	QuestionID int not null,
	Question nvarchar(100) not null,
	Explaination nvarchar(100)
	Primary Key (QuestionID)
);

Create Table [QuestionStatus](
	QuestionID int not null,
	UserID int not null,
	[Status] int not null,
	Primary Key (QuestionID, UserID)
);

Create Table [Answer](
	QuestionID int not null,
	AnswerID int not null,
	[Description] nvarchar(100) not null,
	[Role] int not null,
	Primary Key (QuestionID, AnswerID)
);

Create Table [UserAnswer](
	UserID int not null,
	QuestionID int not null,
	AnswerID int not null,
	Primary Key (UserID, QuestionID)
);

Create Table [QuizStatus](
	UserID int not null,
	QuizID int not null,
	[Status] int not null,
	Mark float
	Primary Key (UserID, QuizID)
);

Alter Table [Session] with nocheck
	Add Foreign Key (UserID) References [User](UserID)
Alter Table [Order] with nocheck
	Add Foreign Key (UserID) References [User](UserID)
Alter Table [QuestionStatus] with nocheck
	Add Foreign Key (UserID) References [User](UserID)
Alter Table [QuizStatus] with nocheck
	Add Foreign Key (UserID) References [User](UserID)
Alter Table [Discount] with nocheck
	Add Foreign Key (LessonID) References [Lesson](LessonID)
Alter Table [User] with nocheck
	Add Foreign Key (SecurityQuestionID) References [SEQuestion](SecurityQuestionID)
Alter Table [Feedback] with nocheck
	Add Foreign Key (LessonID) References [Lesson](LessonID)
Alter Table [Cart] with nocheck
	Add Foreign Key (LessonID) References [Lesson](LessonID)
Alter Table [OrderLesson] with nocheck
	Add Foreign Key (LessonID) References [Lesson](LessonID)
Alter Table [Quiz] with nocheck
	Add Foreign Key (LessonID) References [Lesson](LessonID)
Alter Table [Cart] with nocheck
	Add Foreign Key (SessionID) References [Session](SessionID)
Alter Table [OrderLesson] with nocheck
	Add Foreign Key (OrderID) References [Order](OrderID)
Alter Table [Question] with nocheck
	Add Foreign Key (QuizID) References [Quiz](QuizID)
Alter Table [QuizStatus] with nocheck
	Add Foreign Key (QuizID) References [Quiz](QuizID)
Alter Table [QuestionStatus] with nocheck
	Add Foreign Key (QuestionID) References [Question](QuestionID)
Alter Table [Answer] with nocheck
	Add Foreign Key (QuestionID) References [Question](QuestionID)
Alter Table [UserAnswer] with nocheck
	Add Foreign Key (UserID) References [User](UserID)
Alter Table [UserAnswer] with nocheck
	Add Foreign Key (QuestionID) References [Question](QuestionID)

Insert Into [SEQuestion] Values
(1, 'What');

Insert Into [User] Values 
(1, 'a', 'a', 'reotonaro@gmail.com', 'Huy', '2004-04-04', 1, 'no', 3, 1),
(2, 'b', 'b', 'hi2otaku@gmail.com', 'Huya', '2004-04-04', 1, 'no', 1, 1);

Insert Into [Lesson] Values
(1, 'Math', 10, 'Mathematic', '2024-5-28');

Insert Into [Discount] Values
(1, 20);

Insert Into [Quiz] Values
(1, 1, 'Math 1', '2024-5-28'),
(2, 1, 'Math 2', '2024-5-29');

Insert Into [Question] Values
(1, 1, '1 + 1 = ?', 'Nothing'),
(1, 2, '1 + 2 = ?', 'Nothing'),
(1, 3, '1 + 3 = ?', 'Nothing'),
(1, 4, '1 + 4 = ?', 'Nothing'),
(1, 5, '1 + 4 = ?', 'Nothing'),
(1, 6, '1 + 4 = ?', 'Nothing'),
(1, 7, '1 + 4 = ?', 'Nothing'),
(1, 8, '1 + 4 = ?', 'Nothing'),
(1, 9, '1 + 4 = ?', 'Nothing');


Insert Into [Answer] Values
(1, 1, '2', 1),
(1, 2, '3', 2),
(1, 3, '4', 2),
(2, 1, '2', 2),
(2, 2, '3', 1),
(2, 3, '4', 2),
(3, 1, '3', 2),
(3, 2, '4', 1),
(3, 3, '5', 2),
(4, 1, '3', 2),
(4, 2, '4', 2),
(4, 3, '5', 1);