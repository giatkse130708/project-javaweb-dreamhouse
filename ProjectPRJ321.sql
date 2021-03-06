USE [master]
GO
/****** Object:  Database [ProjectPRJ321]    Script Date: 12/15/2019 1:36:46 PM ******/
CREATE DATABASE [ProjectPRJ321]
GO
ALTER DATABASE [ProjectPRJ321] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [ProjectPRJ321].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [ProjectPRJ321] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [ProjectPRJ321] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [ProjectPRJ321] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [ProjectPRJ321] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [ProjectPRJ321] SET ARITHABORT OFF 
GO
ALTER DATABASE [ProjectPRJ321] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [ProjectPRJ321] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [ProjectPRJ321] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [ProjectPRJ321] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [ProjectPRJ321] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [ProjectPRJ321] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [ProjectPRJ321] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [ProjectPRJ321] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [ProjectPRJ321] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [ProjectPRJ321] SET  DISABLE_BROKER 
GO
ALTER DATABASE [ProjectPRJ321] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [ProjectPRJ321] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [ProjectPRJ321] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [ProjectPRJ321] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [ProjectPRJ321] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [ProjectPRJ321] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [ProjectPRJ321] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [ProjectPRJ321] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [ProjectPRJ321] SET  MULTI_USER 
GO
ALTER DATABASE [ProjectPRJ321] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [ProjectPRJ321] SET DB_CHAINING OFF 
GO
ALTER DATABASE [ProjectPRJ321] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [ProjectPRJ321] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [ProjectPRJ321] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [ProjectPRJ321] SET QUERY_STORE = OFF
GO
USE [ProjectPRJ321]
GO
ALTER DATABASE SCOPED CONFIGURATION SET IDENTITY_CACHE = ON;
GO
ALTER DATABASE SCOPED CONFIGURATION SET LEGACY_CARDINALITY_ESTIMATION = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET LEGACY_CARDINALITY_ESTIMATION = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET MAXDOP = 0;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET MAXDOP = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET PARAMETER_SNIFFING = ON;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET PARAMETER_SNIFFING = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET QUERY_OPTIMIZER_HOTFIXES = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET QUERY_OPTIMIZER_HOTFIXES = PRIMARY;
GO
USE [ProjectPRJ321]
GO
/****** Object:  Table [dbo].[tblCarts]    Script Date: 12/15/2019 1:36:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblCarts](
	[ID] [int] NOT NULL,
	[UserID] [nvarchar](50) NULL,
 CONSTRAINT [PK_Cards] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblImages]    Script Date: 12/15/2019 1:36:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblImages](
	[ID] [int] NOT NULL,
	[ItemID] [int] NULL,
	[Link] [nvarchar](256) NULL,
 CONSTRAINT [PK_tblImages] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblItems]    Script Date: 12/15/2019 1:36:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblItems](
	[ID] [int] NOT NULL,
	[Name] [nvarchar](256) NULL,
	[Address] [nvarchar](256) NULL,
	[Square] [float] NULL,
	[Price] [numeric](18, 0) NULL,
	[Category] [nvarchar](50) NULL,
	[Deleted] [bit] NULL,
	[isAvailable] [bit] NULL,
	[Description] [nvarchar](max) NULL,
 CONSTRAINT [PK_tblItems] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblItemsInCart]    Script Date: 12/15/2019 1:36:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblItemsInCart](
	[CartID] [int] NOT NULL,
	[ItemID] [int] NOT NULL,
 CONSTRAINT [PK_tblItemsInCard] PRIMARY KEY CLUSTERED 
(
	[ItemID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblUsers]    Script Date: 12/15/2019 1:36:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblUsers](
	[UserID] [nvarchar](50) NOT NULL,
	[Password] [nvarchar](50) NULL,
	[Username] [nvarchar](50) NULL,
	[PhoneNumber] [nvarchar](50) NULL,
	[Email] [nvarchar](50) NULL,
	[IdentifyCard] [nvarchar](50) NULL,
	[Deleted] [bit] NULL,
	[Role] [nvarchar](50) NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[tblCarts] ([ID], [UserID]) VALUES (1, N'sa')
INSERT [dbo].[tblCarts] ([ID], [UserID]) VALUES (2, N'gia')
INSERT [dbo].[tblImages] ([ID], [ItemID], [Link]) VALUES (7, 13, N'resources/items/song-zigbee-ung-dung.jpg')
INSERT [dbo].[tblImages] ([ID], [ItemID], [Link]) VALUES (9, 1, N'resources/items/item2_pic1.jpg')
INSERT [dbo].[tblImages] ([ID], [ItemID], [Link]) VALUES (10, 2, N'resources/items/item2_pic2.jpg')
INSERT [dbo].[tblImages] ([ID], [ItemID], [Link]) VALUES (21, 6, N'resources/items/item3_pic1.jpg')
INSERT [dbo].[tblImages] ([ID], [ItemID], [Link]) VALUES (22, 6, N'resources/items/item3_pic2.jpg')
INSERT [dbo].[tblImages] ([ID], [ItemID], [Link]) VALUES (23, 6, N'resources/items/item3_pic3.jpg')
INSERT [dbo].[tblImages] ([ID], [ItemID], [Link]) VALUES (24, 7, N'resources/items/item4_pic1.jpg')
INSERT [dbo].[tblImages] ([ID], [ItemID], [Link]) VALUES (25, 7, N'resources/items/item4_pic2.jpg')
INSERT [dbo].[tblImages] ([ID], [ItemID], [Link]) VALUES (26, 7, N'resources/items/item4_pic3.jpg')
INSERT [dbo].[tblItems] ([ID], [Name], [Address], [Square], [Price], [Category], [Deleted], [isAvailable], [Description]) VALUES (1, N'MỞ BÁN GIỎ HÀNG NỘI BỘ SAIGON PEARL 20 CĂN HỘ SIÊU ĐẸP, GIÁ SIÊU TỐT VÀ GIỎ HÀNG SUNWAH PEARL', N'Thành phố Hòa bình', 100.19999694824219, CAST(2000000000 AS Numeric(18, 0)), N'Nhà xưởng, nhà kho', 0, 1, N'Giỏ hàng nội bộ với 20 căn hộ trống - view đẹp, giá tốt chỉ từ 4 tỷ.
Chương trình ưu đãi cho khách hàng trong năm 2019
* Hỗ trợ lắp đặt tư vấn nội thất giá xưởng.
+ Hoàn thiện nội thất 150 triệu cho căn 2 phòng ngủ.
+ Hoàn thiện nội thất 200 triệu cho căn 3 phòng ngủ.
* Cam kết cho thuê trong vòng 1 tháng.
* Chỉ còn 6 căn 2PN giá tốt nhất thị trường.

Liên hệ PKD hỗ trợ tư vấn và tham quan căn hộ thực tế.
Hotline: 0911.285.825 (Thuỵ Vy).
Email: sales@sunwah.vn.

* Liên hệ PKD hỗ trợ tư vấn và tham quan căn hộ thực tế.
* Hotline: 0911.285.825 (Thuỵ Vy).')
INSERT [dbo].[tblItems] ([ID], [Name], [Address], [Square], [Price], [Category], [Deleted], [isAvailable], [Description]) VALUES (2, N'SANG NHƯỢNG VINHOMES CENTRAL PARK GIÁ TỐT, 1PN CHỈ 3TỶ, 2PN CHỈ TỪ 4 TỶ, 3PN BÁN LỖ: 0903163021', N'Hà nội', 200, CAST(1560000000 AS Numeric(18, 0)), N'Khu nghỉ dưỡng', 0, 1, N'Không description')
INSERT [dbo].[tblItems] ([ID], [Name], [Address], [Square], [Price], [Category], [Deleted], [isAvailable], [Description]) VALUES (6, N'ĐI ĐỊNH CƯ Ở CÙNG CON GÁI NÊN BÁN LÔ ĐẤT 360M2 MẶT TIỀN ĐƯỜNG NHỰA 16M, KỀ TRƯỜNG ĐẠI HỌC, KCN, CHỢ', N'Thành phố Thủ Dầu Một - Bình Dương', 180, CAST(850000000 AS Numeric(18, 0)), N'Chung cư', 0, 1, N'Gọi tôi: 0933 680 519 Lam.

Hiện tại gia đình tôi không muốn quản lý mà tập trung ở cùng con cái nên giờ đành bán gấp lô đất 360m2 = 12mx30m mà gia đình mua năm 2009 định để xây trọ. Gia đình mong muốn gặp được người thiện chí mua giúp đỡ.
* Thông tin lô đất như sau:
Diện tích là 360m2 = 12mx30m mặt tiền đường nhựa 16m. Thổ cư 100% sổ hồng riêng ghi hẳn hoi đất ở tại đô thị do trực tiếp tôi đứng tên.
Giá tôi bán là 850 triệu 180m2 là 6mx30m thôi.
Tôi đã tách làm 2 sổ. Giá cả còn thương lượng chút ít.
Ai thấy thông tin lô đất phù hợp thì gọi điện trực tiếp cho tôi. Lam 0933 680 519. Cảm ơn.
Mua sẽ trực tiếp ra phòng công chứng để ký sang tên.
Nếu muốn an tâm điện thoai đi tôi gửi sổ cho xem trước.
Xung quanh tiện ích không thiếu gì, 100m là chợ dân sinh, 300m là trường cấp 2, công viên trạm xá khu du lịch sinh thái trong phạm vi 500m.
Quan trọng là cách miến đất 200m có trường đại học đang xây, xây cả kí túc xá cho sinh viên nên về đây đầu từ hay muốn tự kinh doanh đều rất ngon.
- Cách đó 500m là có khu nhà máy của Korea đang hoạt động rồi nên công nhân khu vực đông có thể xây trọ để đầu tư.
Tôi không tiếp trung gian môi giới nên đừng điện thoại hỏi chi.
Ai thấy thông tin lô đất phù hợp thì gọi điện trực tiếp cho tôi. Lam 0933 680 519. Cảm ơn.')
INSERT [dbo].[tblItems] ([ID], [Name], [Address], [Square], [Price], [Category], [Deleted], [isAvailable], [Description]) VALUES (7, N'CHUYỂN NHƯỢNG CĂN HỘ 1PN-2PN-3PN MASTERI THẢO ĐIỀN ĐÃ CÓ SỔ HỒNG, GIÁ CỰC TỔT 0902340994', N'Masteri Thảo Điền - Quận 2 - Hồ Chí Minh', 70.5, CAST(10 AS Numeric(18, 0)), N'Chung cư', 0, 1, N'Quỳnh: 0902340994 (Zalo, Viber, SMS, Call).

Hiện tại, em đang nắm giữ nhiều căn Masteri cần chuyển nhượng lại với giá cực thấp so với thị trường, anh chị vui lòng liên hệ, em sẽ có căn giá tốt nhất kèm hình ảnh để anh chị lựa chọn.

Căn hộ 1 phòng ngủ: Bán từ 2.85 tỷ - 3.2 tỷ.

Căn hộ 2 phòng ngủ, 63m2 - 74m2: Bán từ 3.45 - 4.2 tỷ.

Căn hộ 3 phòng ngủ, 90m2 - 95m2: Bán từ 4.7 - 6 tỷ.

Dưới đây là một số căn tiêu biểu:

Căn hộ 1 phòng ngủ, 47,75m2:

- T2 - 1x giá 2.95 tỷ full nội thất.

- T3 - 1x giá 2,95 tỷ full nội thất.')
INSERT [dbo].[tblItems] ([ID], [Name], [Address], [Square], [Price], [Category], [Deleted], [isAvailable], [Description]) VALUES (13, N'Title thứ 3', N'Thành phố Hòa bình', 11.300000190734863, CAST(1500000000 AS Numeric(18, 0)), N'Chung cư', 0, 1, N'aaaaaaaaaaaaaaaa')
INSERT [dbo].[tblItems] ([ID], [Name], [Address], [Square], [Price], [Category], [Deleted], [isAvailable], [Description]) VALUES (14, N'title', N'Thành phố Hòa bình', 100.19999694824219, CAST(1500000000 AS Numeric(18, 0)), N'Nhà xưởng, nhà kho', 0, 0, N'')
INSERT [dbo].[tblItemsInCart] ([CartID], [ItemID]) VALUES (1, 14)
INSERT [dbo].[tblUsers] ([UserID], [Password], [Username], [PhoneNumber], [Email], [IdentifyCard], [Deleted], [Role]) VALUES (N'gia', N'1', N'Trần Khả Gia', N'0369362767', N'giatkt1598@gmail.com', N'233307388', 0, N'member')
INSERT [dbo].[tblUsers] ([UserID], [Password], [Username], [PhoneNumber], [Email], [IdentifyCard], [Deleted], [Role]) VALUES (N'sa', N'123', N'admin', N'0977425672', N'giatkse130708@fpt.edu.vn', N'233307388', 0, N'admin')
ALTER TABLE [dbo].[tblCarts]  WITH CHECK ADD  CONSTRAINT [FK_tblCards_tblUsers] FOREIGN KEY([UserID])
REFERENCES [dbo].[tblUsers] ([UserID])
GO
ALTER TABLE [dbo].[tblCarts] CHECK CONSTRAINT [FK_tblCards_tblUsers]
GO
ALTER TABLE [dbo].[tblImages]  WITH CHECK ADD  CONSTRAINT [FK_tblImages_tblItems] FOREIGN KEY([ItemID])
REFERENCES [dbo].[tblItems] ([ID])
GO
ALTER TABLE [dbo].[tblImages] CHECK CONSTRAINT [FK_tblImages_tblItems]
GO
ALTER TABLE [dbo].[tblItemsInCart]  WITH CHECK ADD  CONSTRAINT [FK_tblItemsInCard_tblCards] FOREIGN KEY([CartID])
REFERENCES [dbo].[tblCarts] ([ID])
GO
ALTER TABLE [dbo].[tblItemsInCart] CHECK CONSTRAINT [FK_tblItemsInCard_tblCards]
GO
ALTER TABLE [dbo].[tblItemsInCart]  WITH CHECK ADD  CONSTRAINT [FK_tblItemsInCard_tblItems] FOREIGN KEY([ItemID])
REFERENCES [dbo].[tblItems] ([ID])
GO
ALTER TABLE [dbo].[tblItemsInCart] CHECK CONSTRAINT [FK_tblItemsInCard_tblItems]
GO
USE [master]
GO
ALTER DATABASE [ProjectPRJ321] SET  READ_WRITE 
GO
