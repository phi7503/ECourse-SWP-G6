Create Database Testing
Go

Use Testing

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

Create Table [Course](
	CourseID int not null,
	CourseName nvarchar(100) not null,
	Price float not null,
	[Description] nvarchar(500),
	CreateDate date not null,
	Primary Key(CourseID)
);

Create Table [Lesson](
	CourseID int not null,
	LessonID int not null,
	LessonName nvarchar(100) not null,	
	[Description] nvarchar(500),	
	Primary Key(CourseID, LessonID)
);


Create Table [Quiz](
	CourseID int not null,
	LessonID int not null,
	QuizID int not null,
	QuizName nvarchar(100) not null,
	CreateDate date not null,
	Primary Key (CourseID, LessonID, QuizID)
);

Create Table [Question](
	CourseID int not null, 
	LessonID int not null,
	QuizID int not null,
	QuestionID int not null,
	Question nvarchar(100) not null,
	Explaination nvarchar(100)
	Primary Key (CourseID, LessonID, QuizID, QuestionID)
);

Create Table [Answer](
	CourseID int not null,
	LessonID int not null,
	QuizID int not null,
	QuestionID int not null,
	AnswerID int not null,
	[Description] nvarchar(100) not null,
	[Role] int not null,
	Primary Key (CourseID, LessonID, QuizID, QuestionID, AnswerID)
);

Create Table [UserAnswer](
	UserID int not null,
	AttemptID int not null,
	CourseID int not null,
	LessonID int not null,
	QuizID int not null,
	QuestionID int not null,	
	AnswerID int not null,
	Primary Key (UserID, AttemptID, CourseID, LessonID, QuizID, QuestionID, AnswerID)
);

Create Table [Attempt](
	UserID int not null,
	CourseID int not null,
	LessonID int not null,
	QuizID int not null,
	AttemptID int not null,
	Primary Key (UserID, CourseID, LessonID, QuizID, AttemptID)
);

Create Table [Discount](
	CourseID int not null,
	[Percentage] float not null,
	Primary Key (CourseID)
);

Create Table [Feedback](
	CourseID int not null,
	FeedbackID int not null,	
	Title nvarchar(500) not null,
	[Description] nvarchar(500) not null,
	Primary Key (FeedbackID)
);

Create Table [Cart](
	UserID int not null,
	CourseID int not null,	
	Primary Key (UserID, CourseID)
);

Create Table [Order](
	OrderID int not null,
	UserID int not null,
	[Status] int not null,
	Primary Key (OrderID)
);

Create Table [OwnCourse](
	CourseID int not null,
	UserID int not null,
	OrderID int not null,
	Primary Key (CourseID, UserID, OrderID)
);

Alter Table [Attempt] with nocheck
	Add Foreign Key (UserID) References [User](UserID)
Alter Table [OwnCourse] with nocheck
	Add Foreign Key (UserID) References [User](UserID)
Alter Table [Order] with nocheck
	Add Foreign Key (UserID) References [User](UserID)
Alter Table [User] with nocheck
	Add Foreign Key (SecurityQuestionID) References [SEQuestion](SecurityQuestionID)
Alter Table [Cart] with nocheck
	Add Foreign Key (UserID) References [User](UserID)
Alter Table [Discount] with nocheck
	Add Foreign Key (CourseID) References [Course](CourseID)
Alter Table [Feedback] with nocheck
	Add Foreign Key (CourseID) References [Course](CourseID)
Alter Table [Cart] with nocheck
	Add Foreign Key (CourseID) References [Course](CourseID)
Alter Table [OwnCourse] with nocheck
	Add Foreign Key (CourseID) References [Course](CourseID)
Alter Table [Lesson] with nocheck
	Add Foreign Key (CourseID) References [Course](CourseID)
Alter Table [Quiz] with nocheck
	Add Foreign Key (CourseID, LessonID) References [Lesson](CourseID, LessonID)
Alter Table [Question] with nocheck
	Add Foreign Key (CourseID, LessonID, QuizID) References [Quiz](CourseID, LessonID, QuizID)
Alter Table [Answer] with nocheck
	Add Foreign Key (CourseID, LessonID, QuizID, QuestionID) References [Question](CourseID, LessonID, QuizID, QuestionID)
Alter Table [Attempt] with nocheck
	Add Foreign Key (CourseID, LessonID, QuizID) References [Quiz](CourseID, LessonID, QuizID)
Alter Table [UserAnswer] with nocheck 
	Add Foreign Key (UserID, CourseID, LessonID, QuizID, AttemptID) References [Attempt](UserID, CourseID, LessonID, QuizID, AttemptID)
Alter Table [UserAnswer] with nocheck
	Add Foreign Key (CourseID, LessonID, QuizID, QuestionID, AnswerID) References [Answer](CourseID, LessonID, QuizID, QuestionID, AnswerID)
Alter Table [OwnCourse] with nocheck
	Add Foreign Key (OrderID) References [Order](OrderID)