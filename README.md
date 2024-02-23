#Shop handmade

Đồ án chuyên ngành webiste bán đồ handmade 

Tổng quan project:

+Phần back end :Rest API được viết bằng java spring boot được đặt trong thư mục con backend

+Phần front end:Angular tạo giao diện và nhận API từ back end và xử lý hiển thị cho người dùng đặt trong thư mục con frontend

+File DB trong mysql, SQL Server Management Studio,db mặc định xài là my sql,vì sử dụng java spring data nên khi cấu trúc xong sẽ sinh ra db từ back end.

+Có các file db chứa dữ liệu được export từ my sql đặt trong thư mục Dump20231219

+Bao gồm các file khác như lược đồ,báo cáo.

#Hướng dẫn sử dụng:
Có thể truy cập trực tiếp đường link dưới để sử dụng,reponse tương thích trên cả mobile và pc,
https://frontend-production-9a0c.up.railway.app/
-Về phía khách hàng:
+Tầi khoản có sẵn :0866715111 %% kK123456@
+Gồm chức năng như đăng kí,đăng nhập,xem danh sách,chi tiết sản phẩm.Tìm kiếm sản phẩm theo tên,theo danh mục.Có các chức năng khác như giỏ hàng,đặt hàng,sản phẩm yêu thích,tra cứu đơn hàng,cập nhật thông tin.
Về phia Admin:
+Tài khoản:0866715211 && vV123456@
+Gồm các chức năng crud sản phẩm,danh mục,cập nhật trạng thái đơn hàng

#Hướng dẫn cài dặt:Trước đó đã cài đặt các phần mềm như Mysql, SQL Server Management Studio,intel jda or eclise,visul studio
+Download,clone repository về.
+Tạo Db mới rỗng hoặc tạo dbshop handmade trong mysql và import db vào từ thư mục Dump20231219
+Mở project back end bằng intel jda hoặc eclise,cấu hình đường dẫn đến db,port,hệ csdl trong file porm.xml
+Mở project front end bằng visul studio,Chỉnh sửa đường dẫn kết nối với back end đặt trong component enviroment,yarn start để chạy.

