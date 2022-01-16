USE [master]
GO
/****** Object:  Database [CodeCademy12]    Script Date: 16/01/2022 19:11:09 ******/
CREATE DATABASE [CodeCademy12]
CONTAINMENT = NONE
GO
ALTER DATABASE [CodeCademy12] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [CodeCademy12].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [CodeCademy12] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [CodeCademy12] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [CodeCademy12] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [CodeCademy12] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [CodeCademy12] SET ARITHABORT OFF 
GO
ALTER DATABASE [CodeCademy12] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [CodeCademy12] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [CodeCademy12] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [CodeCademy12] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [CodeCademy12] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [CodeCademy12] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [CodeCademy12] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [CodeCademy12] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [CodeCademy12] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [CodeCademy12] SET  ENABLE_BROKER 
GO
ALTER DATABASE [CodeCademy12] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [CodeCademy12] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [CodeCademy12] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [CodeCademy12] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [CodeCademy12] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [CodeCademy12] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [CodeCademy12] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [CodeCademy12] SET RECOVERY FULL 
GO
ALTER DATABASE [CodeCademy12] SET  MULTI_USER 
GO
ALTER DATABASE [CodeCademy12] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [CodeCademy12] SET DB_CHAINING OFF 
GO
ALTER DATABASE [CodeCademy12] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [CodeCademy12] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [CodeCademy12] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [CodeCademy12] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'CodeCademy12', N'ON'
GO
ALTER DATABASE [CodeCademy12] SET QUERY_STORE = OFF
GO
USE [CodeCademy12]
GO
/****** Object:  Table [dbo].[Certificate]    Script Date: 16/01/2022 19:11:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Certificate](
	[CertificateID] [int] IDENTITY(1,1) NOT NULL,
	[Rating] [int] NOT NULL,
	[StaffID] [int] NOT NULL,
	[CourseID] [int] NULL,
	[CourseTakerID] [int] NOT NULL,
 CONSTRAINT [UK_UniqueCertificate] UNIQUE (Rating, StaffID, CourseID, CourseTakerID),
 CONSTRAINT [PK_Certificate] PRIMARY KEY CLUSTERED 
(
	[CertificateID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ContactPerson]    Script Date: 16/01/2022 19:11:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ContactPerson](
	[ContactPersonID] [int] IDENTITY(1,1) NOT NULL,
	[Email] [nvarchar](320) NOT NULL,
	[FirstName] [nvarchar](20) NOT NULL,
	[LastName] [nvarchar](20) NOT NULL,

 CONSTRAINT [UK_UniqueContactPerson] UNIQUE (FirstName, LastName, Email),
 CONSTRAINT [PK_ContactPerson] PRIMARY KEY CLUSTERED 
(
	[ContactPersonID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UK_ContactPerson] UNIQUE NONCLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ContentItem]    Script Date: 16/01/2022 19:11:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ContentItem](
	[ContentId] [int] IDENTITY(1,1) NOT NULL,
	[PublicationDate] [date] NOT NULL,
	[Status] [nvarchar](128) NOT NULL,
	[WebcastID] [int] NULL,
	[CourseID] [int] NULL,
 CONSTRAINT [UK_uniqueContentItem] UNIQUE (PublicationDate, WebcastID, CourseID, [Status]),
 CONSTRAINT [PK_ContentItem] PRIMARY KEY CLUSTERED 
(
	[ContentId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ContentItem_Voortgang]    Script Date: 16/01/2022 19:11:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ContentItem_Voortgang](
	[CourseTakerID] [int] NOT NULL,
	[ContentID] [int] NOT NULL,
	[PercentageViewed] [float] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Course]    Script Date: 16/01/2022 19:11:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Course](
	[CourseID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](128) NOT NULL,
	[Topic] [nvarchar](128) NOT NULL,
	[Text] [nvarchar](256) NOT NULL,
	[LvL] [nvarchar](256) NOT NULL,
	[IsRecommended] [int] NULL,
 CONSTRAINT [UK_UniqueCourse] UNIQUE ([Name], Topic, [Text], LvL),
 CONSTRAINT [PK_Course] PRIMARY KEY CLUSTERED 
(
	[CourseID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UK_Course] UNIQUE NONCLUSTERED 
(
	[Name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CourseTaker]    Script Date: 16/01/2022 19:11:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CourseTaker](
	[CourseTakerID] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_CourseTaker_1] PRIMARY KEY CLUSTERED 
(
	[CourseTakerID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Module]    Script Date: 16/01/2022 19:11:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Module](
	[ModuleID] [int] IDENTITY(1,1) NOT NULL,
	[Title] [nvarchar](128) NOT NULL,
	[Version] [int] NOT NULL,
	[SerialNumber] [int] NOT NULL,
	[Description] [nvarchar](1024) NULL,
	[ContactPersonID] [int] NULL,
	[CourseID] [int] NULL,
 CONSTRAINT [PK_Module] PRIMARY KEY CLUSTERED 
(
	[ModuleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UK_Module] UNIQUE NONCLUSTERED 
(
	[Title] ASC,
	[Version] ASC,
	[SerialNumber] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Registration]    Script Date: 16/01/2022 19:11:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Registration](
	[RegistrationID] [int] IDENTITY(1,1) NOT NULL,
	[CourseTakerID] [int] NOT NULL,
	[CourseID] [int] NULL,
	[RegistrationDate] [date] NOT NULL,
 CONSTRAINT [PK_Registration] PRIMARY KEY CLUSTERED 
(
	[RegistrationID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UK_Registration] UNIQUE NONCLUSTERED 
(
	[CourseID] ASC,
	[CourseTakerID] ASC,
	[RegistrationDate] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Speaker]    Script Date: 16/01/2022 19:11:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Speaker](
	[SpeakerID] [int] IDENTITY(1,1) NOT NULL,
	[FirstName] [nvarchar](256) NOT NULL,
	[LastName] [nvarchar](256) NOT NULL,
	[Organisation] [nvarchar](256) NOT NULL,
 CONSTRAINT [PK_Speaker] PRIMARY KEY CLUSTERED 
(
	[SpeakerID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UK_UniqueNameAndOrg] UNIQUE NONCLUSTERED 
(
	[FirstName] ASC,
	[LastName] ASC,
	[Organisation] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Staff]    Script Date: 16/01/2022 19:11:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Staff](
	[StaffID] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_Staff_1] PRIMARY KEY CLUSTERED 
(
	[StaffID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User]    Script Date: 16/01/2022 19:11:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[Email] [nvarchar](320) NOT NULL,
	[FirstName] [nvarchar](128) NOT NULL,
	[LastName] [nvarchar](128) NOT NULL,
	[DateOfBirth] [date] NOT NULL,
	[Gender] [nvarchar](50) NOT NULL,
	[Address] [nvarchar](128) NOT NULL,
	[PostalCode] [nvarchar](8) NOT NULL,
	[Residence] [nvarchar](128) NOT NULL,
	[Country] [nvarchar](128) NOT NULL,
	[CourseTakerID] [int] NULL,
	[StaffID] [int] NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Webcast]    Script Date: 16/01/2022 19:11:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Webcast](
	[WebcastID] [int] IDENTITY(1,1) NOT NULL,
	[Title] [nvarchar](128) NOT NULL,
	[URL] [nvarchar](128) NOT NULL,
	[Description] [nvarchar](1024) NULL,
	[Duration] [int] NOT NULL,
	[SpeakerID] [int] NULL,
 CONSTRAINT [PK_Webcast] PRIMARY KEY CLUSTERED 
(
	[WebcastID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UK_Webcast] UNIQUE NONCLUSTERED 
(
	[Title] ASC,
	[URL] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Certificate] ON 
GO
INSERT [dbo].[Certificate] ([CertificateID], [Rating], [StaffID], [CourseID], [CourseTakerID]) VALUES (1, 9, 1002, 3011, 1005)
GO
INSERT [dbo].[Certificate] ([CertificateID], [Rating], [StaffID], [CourseID], [CourseTakerID]) VALUES (5, 7, 2002, 3011, 2002)
GO
SET IDENTITY_INSERT [dbo].[Certificate] OFF
GO
SET IDENTITY_INSERT [dbo].[ContactPerson] ON 
GO
INSERT [dbo].[ContactPerson] ([ContactPersonID], [Email], [FirstName], [LastName]) VALUES (1, N'billgates@hotmail.com', N'Bill', N'Gates')
GO
INSERT [dbo].[ContactPerson] ([ContactPersonID], [Email], [FirstName], [LastName]) VALUES (2, N's.jobs@apple.com', N'Steve', N'Jobs')
GO
INSERT [dbo].[ContactPerson] ([ContactPersonID], [Email], [FirstName], [LastName]) VALUES (3, N'lhh.weterings@student.avans.nl', N'Laurens', N'Weterings')
GO
SET IDENTITY_INSERT [dbo].[ContactPerson] OFF
GO
SET IDENTITY_INSERT [dbo].[ContentItem] ON 
GO
INSERT [dbo].[ContentItem] ([ContentId], [PublicationDate], [Status], [WebcastID], [CourseID]) VALUES (2, CAST(N'1970-01-01' AS Date), N'ARCHIVED', NULL, 3017)
GO
INSERT [dbo].[ContentItem] ([ContentId], [PublicationDate], [Status], [WebcastID], [CourseID]) VALUES (3, CAST(N'1970-01-01' AS Date), N'ACTIVE', NULL, 3018)
GO
INSERT [dbo].[ContentItem] ([ContentId], [PublicationDate], [Status], [WebcastID], [CourseID]) VALUES (4, CAST(N'1970-01-01' AS Date), N'CONCEPT', NULL, 3019)
GO
INSERT [dbo].[ContentItem] ([ContentId], [PublicationDate], [Status], [WebcastID], [CourseID]) VALUES (5, CAST(N'2022-01-30' AS Date), N'ACTIVE', NULL, 3020)
GO
INSERT [dbo].[ContentItem] ([ContentId], [PublicationDate], [Status], [WebcastID], [CourseID]) VALUES (8, CAST(N'2022-01-30' AS Date), N'ACTIVE', NULL, 3016)
GO
INSERT [dbo].[ContentItem] ([ContentId], [PublicationDate], [Status], [WebcastID], [CourseID]) VALUES (9, CAST(N'2022-01-22' AS Date), N'CONCEPT', NULL, 3021)
GO
INSERT [dbo].[ContentItem] ([ContentId], [PublicationDate], [Status], [WebcastID], [CourseID]) VALUES (10, CAST(N'2022-01-22' AS Date), N'ARCHIVED', NULL, 3021)
GO
INSERT [dbo].[ContentItem] ([ContentId], [PublicationDate], [Status], [WebcastID], [CourseID]) VALUES (13, CAST(N'2022-01-16' AS Date), N'ACTIVE', 18, NULL)
GO
SET IDENTITY_INSERT [dbo].[ContentItem] OFF
GO
INSERT [dbo].[ContentItem_Voortgang] ([CourseTakerID], [ContentID], [PercentageViewed]) VALUES (1005, 13, 4.57505300048876)
GO
SET IDENTITY_INSERT [dbo].[Course] ON 
GO
INSERT [dbo].[Course] ([CourseID], [Name], [Topic], [Text], [Lvl], [IsRecommended]) VALUES (3011, N'Course 2', N'Programming', N'Course about programming', N'BEGINNER', NULL)
GO
INSERT [dbo].[Course] ([CourseID], [Name], [Topic], [Text], [Lvl], [IsRecommended]) VALUES (3012, N'Course 3', N'Databases', N'Course Databases', N'BEGINNER', NULL)
GO
INSERT [dbo].[Course] ([CourseID], [Name], [Topic], [Text], [Lvl], [IsRecommended]) VALUES (3016, N'Course 4', N'Databases', N'Advanced course about database', N'ADVANCED', 1)
GO
INSERT [dbo].[Course] ([CourseID], [Name], [Topic], [Text], [Lvl], [IsRecommended]) VALUES (3017, N'Course 1 ', N'Course ', N'Course ', N'BEGINNER', NULL)
GO
INSERT [dbo].[Course] ([CourseID], [Name], [Topic], [Text], [Lvl], [IsRecommended]) VALUES (3018, N'Course 6', N'https://www.google.com', N'Test course 2', N'ADVANCED', NULL)
GO
INSERT [dbo].[Course] ([CourseID], [Name], [Topic], [Text], [Lvl], [IsRecommended]) VALUES (3019, N'Course 7', N'https://www.google.com', N'Test course 3', N'EXPERT', NULL)
GO
INSERT [dbo].[Course] ([CourseID], [Name], [Topic], [Text], [Lvl], [IsRecommended]) VALUES (3020, N'Course 8', N'C++', N'Learn C++', N'BEGINNER', NULL)
GO
INSERT [dbo].[Course] ([CourseID], [Name], [Topic], [Text], [Lvl], [IsRecommended]) VALUES (3021, N'Course 9', N'Requirements Engineering', N'Course about RE', N'EXPERT', NULL)
GO
SET IDENTITY_INSERT [dbo].[Course] OFF
GO
SET IDENTITY_INSERT [dbo].[CourseTaker] ON 
GO
INSERT [dbo].[CourseTaker] ([CourseTakerID]) VALUES (1005)
GO
INSERT [dbo].[CourseTaker] ([CourseTakerID]) VALUES (2002)
GO
INSERT [dbo].[CourseTaker] ([CourseTakerID]) VALUES (3002)
GO
INSERT [dbo].[CourseTaker] ([CourseTakerID]) VALUES (4002)
GO
INSERT [dbo].[CourseTaker] ([CourseTakerID]) VALUES (5002)
GO
INSERT [dbo].[CourseTaker] ([CourseTakerID]) VALUES (5003)
GO
SET IDENTITY_INSERT [dbo].[CourseTaker] OFF
GO
SET IDENTITY_INSERT [dbo].[Module] ON 
GO
INSERT [dbo].[Module] ([ModuleID], [Title], [Version], [SerialNumber], [Description], [ContactPersonID], [CourseID]) VALUES (1030, N'Database connections', 1, 4214, N'Creating application with a database connection', 3, 3012)
GO
INSERT [dbo].[Module] ([ModuleID], [Title], [Version], [SerialNumber], [Description], [ContactPersonID], [CourseID]) VALUES (1033, N'Enums', 2, 124531, N'The use for ENUMS', 1, 3011)
GO
INSERT [dbo].[Module] ([ModuleID], [Title], [Version], [SerialNumber], [Description], [ContactPersonID], [CourseID]) VALUES (1034, N'Database security', 5, 1692, N'Prevent SQL escalation', 2, 3016)
GO
INSERT [dbo].[Module] ([ModuleID], [Title], [Version], [SerialNumber], [Description], [ContactPersonID], [CourseID]) VALUES (1035, N'Programming 1', 1, 123432, N'Module about programming', NULL, 3020)
GO
INSERT [dbo].[Module] ([ModuleID], [Title], [Version], [SerialNumber], [Description], [ContactPersonID], [CourseID]) VALUES (1036, N'Requirements Engineering', 2, 12345, N'Module about RE', NULL, 3021)
GO
INSERT [dbo].[Module] ([ModuleID], [Title], [Version], [SerialNumber], [Description], [ContactPersonID], [CourseID]) VALUES (1037, N'Java Programming', 4, 567153, N'Learn how to program in Java', NULL, NULL)
GO
SET IDENTITY_INSERT [dbo].[Module] OFF
GO
SET IDENTITY_INSERT [dbo].[Registration] ON 
GO
INSERT [dbo].[Registration] ([RegistrationID], [CourseTakerID], [CourseID], [RegistrationDate]) VALUES (17, 1005, 3016, CAST(N'2022-01-16' AS Date))
GO
INSERT [dbo].[Registration] ([RegistrationID], [CourseTakerID], [CourseID], [RegistrationDate]) VALUES (15, 2002, 3017, CAST(N'2022-01-14' AS Date))
GO
INSERT [dbo].[Registration] ([RegistrationID], [CourseTakerID], [CourseID], [RegistrationDate]) VALUES (12, 2002, 3017, CAST(N'2022-01-16' AS Date))
GO
INSERT [dbo].[Registration] ([RegistrationID], [CourseTakerID], [CourseID], [RegistrationDate]) VALUES (11, 1005, 3019, CAST(N'2022-01-16' AS Date))
GO
INSERT [dbo].[Registration] ([RegistrationID], [CourseTakerID], [CourseID], [RegistrationDate]) VALUES (14, 1005, 3019, CAST(N'2022-01-22' AS Date))
GO
INSERT [dbo].[Registration] ([RegistrationID], [CourseTakerID], [CourseID], [RegistrationDate]) VALUES (16, 3002, 3019, CAST(N'2022-01-16' AS Date))
GO
SET IDENTITY_INSERT [dbo].[Registration] OFF
GO
SET IDENTITY_INSERT [dbo].[Speaker] ON 
GO
INSERT [dbo].[Speaker] ([SpeakerID], [FirstName], [LastName], [Organisation]) VALUES (1, N'Arnoldus', N'Blommers', N'Square circle')
GO
INSERT [dbo].[Speaker] ([SpeakerID], [FirstName], [LastName], [Organisation]) VALUES (6, N'Harry', N'Piekema', N'Albert Heijn')
GO
INSERT [dbo].[Speaker] ([SpeakerID], [FirstName], [LastName], [Organisation]) VALUES (2, N'Hendrick', N'Wondergem', N'Leasing')
GO
INSERT [dbo].[Speaker] ([SpeakerID], [FirstName], [LastName], [Organisation]) VALUES (3, N'Kees', N'Janssen', N'Albert Heijn')
GO
INSERT [dbo].[Speaker] ([SpeakerID], [FirstName], [LastName], [Organisation]) VALUES (5, N'Lysanne', N'Veen', N'Hema')
GO
SET IDENTITY_INSERT [dbo].[Speaker] OFF
GO
SET IDENTITY_INSERT [dbo].[Staff] ON 
GO
INSERT [dbo].[Staff] ([StaffID]) VALUES (1002)
GO
INSERT [dbo].[Staff] ([StaffID]) VALUES (1003)
GO
INSERT [dbo].[Staff] ([StaffID]) VALUES (2002)
GO
INSERT [dbo].[Staff] ([StaffID]) VALUES (2003)
GO
INSERT [dbo].[Staff] ([StaffID]) VALUES (2004)
GO
INSERT [dbo].[Staff] ([StaffID]) VALUES (2005)
GO
INSERT [dbo].[Staff] ([StaffID]) VALUES (2006)
GO
INSERT [dbo].[Staff] ([StaffID]) VALUES (3007)
GO
INSERT [dbo].[Staff] ([StaffID]) VALUES (3008)
GO
INSERT [dbo].[Staff] ([StaffID]) VALUES (3009)
GO
INSERT [dbo].[Staff] ([StaffID]) VALUES (3010)
GO
INSERT [dbo].[Staff] ([StaffID]) VALUES (3011)
GO
SET IDENTITY_INSERT [dbo].[Staff] OFF
GO
INSERT [dbo].[User] ([Email], [FirstName], [LastName], [DateOfBirth], [Gender], [Address], [PostalCode], [Residence], [Country], [CourseTakerID], [StaffID]) VALUES (N'FabianoDasselaar@hotmail.nl', N'Fabiano', N'Dasselaar', CAST(N'2005-12-11' AS Date), N'MALE', N'Hugo de Grootstraat 114', N'4206ZG', N'Gorinchem', N'Netherlands', NULL, 1002)
GO
INSERT [dbo].[User] ([Email], [FirstName], [LastName], [DateOfBirth], [Gender], [Address], [PostalCode], [Residence], [Country], [CourseTakerID], [StaffID]) VALUES (N'johndoe@gmail.com', N'John', N'Doe', CAST(N'1970-01-01' AS Date), N'MALE', N'Lovensdijkstraat 10', N'4264 CK', N'Breda', N'Netherlands', NULL, 3011)
GO
INSERT [dbo].[User] ([Email], [FirstName], [LastName], [DateOfBirth], [Gender], [Address], [PostalCode], [Residence], [Country], [CourseTakerID], [StaffID]) VALUES (N'Kees.pieters@gmail.com', N'Kees', N'Pieter', CAST(N'2021-01-19' AS Date), N'MALE', N'Rue du general delestraint 5', N'5931XZ', N'Paris', N'France', 2002, NULL)
GO
INSERT [dbo].[User] ([Email], [FirstName], [LastName], [DateOfBirth], [Gender], [Address], [PostalCode], [Residence], [Country], [CourseTakerID], [StaffID]) VALUES (N'MarryYasar@armyspy.com', N'Marry', N'Yasar', CAST(N'1982-04-06' AS Date), N'FEMALE', N'Prinses Beatrixweg 188', N'9001JM', N'Amstelveen', N'Netherlands', 3002, NULL)
GO
INSERT [dbo].[User] ([Email], [FirstName], [LastName], [DateOfBirth], [Gender], [Address], [PostalCode], [Residence], [Country], [CourseTakerID], [StaffID]) VALUES (N'OrhanMohammed@gmail.com', N'Orhan', N'Mohammed', CAST(N'1960-01-19' AS Date), N'MALE', N'Albrecht Durerstraat 168', N'1077LX', N'Amsterdam', N'Netherlands', 1005, NULL)
GO
SET IDENTITY_INSERT [dbo].[Webcast] ON 
GO
INSERT [dbo].[Webcast] ([WebcastID], [Title], [URL], [Description], [Duration], [SpeakerID]) VALUES (18, N'Hema market analysis', N'Hema.nl', N'Hema''s guide for world domination', 100, 5)
GO
INSERT [dbo].[Webcast] ([WebcastID], [Title], [URL], [Description], [Duration], [SpeakerID]) VALUES (19, N'Albert Heijn safety', N'Ah.nl/safety/', N'Ahold Delheize safety precautions', 420, 6)
GO
SET IDENTITY_INSERT [dbo].[Webcast] OFF
GO
ALTER TABLE [dbo].[Registration] ADD  CONSTRAINT [DF_Registration_RegistrationDate]  DEFAULT (getdate()) FOR [RegistrationDate]
GO
ALTER TABLE [dbo].[Certificate]  WITH CHECK ADD  CONSTRAINT [FK_Certificate_Course1] FOREIGN KEY([CourseID])
REFERENCES [dbo].[Course] ([CourseID])
ON UPDATE CASCADE
ON DELETE SET NULL
GO
ALTER TABLE [dbo].[Certificate] CHECK CONSTRAINT [FK_Certificate_Course1]
GO
ALTER TABLE [dbo].[Certificate]  WITH CHECK ADD  CONSTRAINT [FK_Certificate_CourseTaker] FOREIGN KEY([CourseTakerID])
REFERENCES [dbo].[CourseTaker] ([CourseTakerID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Certificate] CHECK CONSTRAINT [FK_Certificate_CourseTaker]
GO
ALTER TABLE [dbo].[Certificate]  WITH CHECK ADD  CONSTRAINT [FK_Certificate_Staff] FOREIGN KEY([StaffID])
REFERENCES [dbo].[Staff] ([StaffID])
GO
ALTER TABLE [dbo].[Certificate] CHECK CONSTRAINT [FK_Certificate_Staff]
GO
ALTER TABLE [dbo].[ContentItem]  WITH CHECK ADD  CONSTRAINT [FK_ContentItem_Course] FOREIGN KEY([CourseID])
REFERENCES [dbo].[Course] ([CourseID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[ContentItem] CHECK CONSTRAINT [FK_ContentItem_Course]
GO
ALTER TABLE [dbo].[ContentItem]  WITH CHECK ADD  CONSTRAINT [FK_ContentItem_Webcast] FOREIGN KEY([WebcastID])
REFERENCES [dbo].[Webcast] ([WebcastID])
GO
ALTER TABLE [dbo].[ContentItem] CHECK CONSTRAINT [FK_ContentItem_Webcast]
GO
ALTER TABLE [dbo].[ContentItem_Voortgang]  WITH CHECK ADD  CONSTRAINT [FK_ContentItem_Voortgang_ContentItem] FOREIGN KEY([ContentID])
REFERENCES [dbo].[ContentItem] ([ContentId])
GO
ALTER TABLE [dbo].[ContentItem_Voortgang] CHECK CONSTRAINT [FK_ContentItem_Voortgang_ContentItem]
GO
ALTER TABLE [dbo].[ContentItem_Voortgang]  WITH CHECK ADD  CONSTRAINT [FK_ContentItem_Voortgang_CourseTaker] FOREIGN KEY([CourseTakerID])
REFERENCES [dbo].[CourseTaker] ([CourseTakerID])
GO
ALTER TABLE [dbo].[ContentItem_Voortgang] CHECK CONSTRAINT [FK_ContentItem_Voortgang_CourseTaker]
GO
ALTER TABLE [dbo].[Course]  WITH NOCHECK ADD  CONSTRAINT [FK_Course_Course] FOREIGN KEY([IsRecommended])
REFERENCES [dbo].[Course] ([CourseID])
GO
ALTER TABLE [dbo].[Course] NOCHECK CONSTRAINT [FK_Course_Course]
GO
ALTER TABLE [dbo].[Module]  WITH CHECK ADD  CONSTRAINT [FK_Module_ContactPerson] FOREIGN KEY([ContactPersonID])
REFERENCES [dbo].[ContactPerson] ([ContactPersonID])
GO
ALTER TABLE [dbo].[Module] CHECK CONSTRAINT [FK_Module_ContactPerson]
GO
ALTER TABLE [dbo].[Module]  WITH CHECK ADD  CONSTRAINT [FK_Module_Course] FOREIGN KEY([CourseID])
REFERENCES [dbo].[Course] ([CourseID])
ON UPDATE CASCADE
ON DELETE SET NULL
GO
ALTER TABLE [dbo].[Module] CHECK CONSTRAINT [FK_Module_Course]
GO
ALTER TABLE [dbo].[Registration]  WITH CHECK ADD  CONSTRAINT [FK_Registration_Course] FOREIGN KEY([CourseID])
REFERENCES [dbo].[Course] ([CourseID])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Registration] CHECK CONSTRAINT [FK_Registration_Course]
GO
ALTER TABLE [dbo].[Registration]  WITH CHECK ADD  CONSTRAINT [FK_Registration_CourseTaker] FOREIGN KEY([CourseTakerID])
REFERENCES [dbo].[CourseTaker] ([CourseTakerID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Registration] CHECK CONSTRAINT [FK_Registration_CourseTaker]
GO
ALTER TABLE [dbo].[User]  WITH CHECK ADD  CONSTRAINT [FK_User_CourseTaker1] FOREIGN KEY([CourseTakerID])
REFERENCES [dbo].[CourseTaker] ([CourseTakerID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[User] CHECK CONSTRAINT [FK_User_CourseTaker1]
GO
ALTER TABLE [dbo].[User]  WITH CHECK ADD  CONSTRAINT [FK_User_Staff] FOREIGN KEY([StaffID])
REFERENCES [dbo].[Staff] ([StaffID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[User] CHECK CONSTRAINT [FK_User_Staff]
GO
ALTER TABLE [dbo].[Webcast]  WITH CHECK ADD  CONSTRAINT [FK_Webcast_Speaker] FOREIGN KEY([SpeakerID])
REFERENCES [dbo].[Speaker] ([SpeakerID])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Webcast] CHECK CONSTRAINT [FK_Webcast_Speaker]
GO
ALTER TABLE [dbo].[Certificate]  WITH CHECK ADD  CONSTRAINT [CK_CertificateRating] CHECK  (([Rating] like '[0-9]'))
GO
ALTER TABLE [dbo].[Certificate] CHECK CONSTRAINT [CK_CertificateRating]
GO
ALTER TABLE [dbo].[ContactPerson]  WITH CHECK ADD  CONSTRAINT [CK_ContactPersonEmail] CHECK  (([Email] like '_%@%___.%__'))
GO
ALTER TABLE [dbo].[ContactPerson] CHECK CONSTRAINT [CK_ContactPersonEmail]
GO
ALTER TABLE [dbo].[ContentItem]  WITH CHECK ADD  CONSTRAINT [CK_ContentItem] CHECK  (([WebcastID] IS NOT NULL AND [CourseID] IS NULL OR [WebcastID] IS NULL AND [CourseID] IS NOT NULL))
GO
ALTER TABLE [dbo].[ContentItem] CHECK CONSTRAINT [CK_ContentItem]
GO
ALTER TABLE [dbo].[User]  WITH CHECK ADD  CONSTRAINT [CK_Email] CHECK  (([Email] like '_%@%___.%__'))
GO
ALTER TABLE [dbo].[User] CHECK CONSTRAINT [CK_Email]
GO
ALTER TABLE [dbo].[User]  WITH CHECK ADD  CONSTRAINT [CK_Gender] CHECK  (([Gender] like 'FEMALE' OR [Gender] like 'Male'))
GO
ALTER TABLE [dbo].[User] CHECK CONSTRAINT [CK_Gender]
GO
ALTER TABLE [dbo].[User]  WITH CHECK ADD  CONSTRAINT [CK_PostalCode] CHECK  (([PostalCode] like '[0-9][0-9][0-9][0-9]%__%'))
GO
ALTER TABLE [dbo].[User] CHECK CONSTRAINT [CK_PostalCode]
GO
ALTER TABLE [dbo].[User]  WITH CHECK ADD  CONSTRAINT [CK_User] CHECK  (([CourseTakerID] IS NOT NULL AND [StaffID] IS NULL OR [CourseTakerID] IS NULL AND [StaffID] IS NOT NULL))
GO
ALTER TABLE [dbo].[User] CHECK CONSTRAINT [CK_User]
GO
ALTER TABLE [dbo].[Webcast]  WITH CHECK ADD  CONSTRAINT [CK_URL] CHECK  (([URL] like '_%.%__'))
GO
ALTER TABLE [dbo].[Webcast] CHECK CONSTRAINT [CK_URL]
GO
USE [master]
GO
ALTER DATABASE [CodeCademy12] SET  READ_WRITE 
GO