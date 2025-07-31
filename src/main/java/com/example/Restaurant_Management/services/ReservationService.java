package com.example.Restaurant_Management.services;

import com.example.Restaurant_Management.dto.request.ReservationRequest;
import com.example.Restaurant_Management.models.Reservation;
import com.example.Restaurant_Management.models.Restaurant;
import com.example.Restaurant_Management.models.Tables;
import com.example.Restaurant_Management.models.Users;
import com.example.Restaurant_Management.repositories.ReservationRepositories;
import com.example.Restaurant_Management.repositories.RestaurantRepositories;
import com.example.Restaurant_Management.repositories.TableRepositories;
import com.example.Restaurant_Management.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepositories repositories;

    @Autowired
    private TableRepositories tableRepositories;

    @Autowired
    private UserRepositories userRepositories;

    @Autowired
    private RestaurantRepositories restaurantRepositories;

    @Transactional
    public String BookingTable(String email, ReservationRequest request) {

        Tables tables = tableRepositories.findById(request.getCapcity())
                .orElseThrow(() -> new RuntimeException("Bàn không tồn tại"));

        Users users = userRepositories.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email không tồn tại"));

        Restaurant restaurant = restaurantRepositories.findById(request.getRestaurantID())
                .orElseThrow(() -> new RuntimeException("Nhà hàng không tồn tại"));

        //Danh sách các bàn đã được đặt
        List<Tables> tablesAvailable = tableRepositories.findByStatus(Tables.Status.AVAILABLE);

        //Kiểm tra bàn đã được đặt
        if (tables.getStatus() == Tables.Status.RESERVED || tables.getStatus() == Tables.Status.OCCUPIED) {
            return "Bàn đẵ được người khác đặt.Vui lòng đặt bàn khác!!!";
        } else if (tables.getStatus() == Tables.Status.OUT_OF_SERVICE) {
            return "Bàn đang sửa chữa.Vui lòng đặt bàn khác!!!";
        }

        //Định dạng ngày tháng năm để đặt bàn
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date dateFormat = null;
        try {
            dateFormat = format.parse(request.getReservationDate());
            Date currentDate = new Date();
            //Kiểm tra không nhập ngày
            if (dateFormat == null) {
                return "Vui lòng nhập ngày cụ thể.";
            }
            //Kiểm tra nhập ngày trong quá khứ
            else if (dateFormat.before(currentDate)) {
                return "Không thể đặt bàn ngày trong quá khứ.";
            }
        } catch (ParseException e) {
            return "Bạn đang sai định dạng.Vui lòng nhập đúng định dang: dd/MM/yyyy.";
        }

        //Định dạng giờ đặt bàn
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime timeFormate = null;
        try {
            LocalTime openingTine = LocalTime.of(11, 0);
            LocalTime closingTine = LocalTime.of(23, 0);

            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now();
            if (timeFormate == null) {
                return "Vui lòng nhập giờ cụ thể";
            } else if (date.equals(dateFormat) && time.isBefore(timeFormate)) {
                return "Không được chọn thời gian đã qua.Vui lòng chọn lại giờ";
            } else if (time.isAfter(closingTine) || time.isBefore(openingTine)) {
                return "Vui lòng chọn trong khoảng 11h đến 23h.";
            }
        } catch (DateTimeParseException exception) {
            return "Bạn đang sai định dạng.Vui lòng nhập đúng định dang: HH:mm.";
        }

        //Thông tin đặt bàn
        try {
            Reservation reservation = new Reservation();

            reservation.setUsers(users);
            reservation.setTable(tables);
            reservation.setRestaurant(restaurant);
            reservation.setReservation_date(dateFormat);
            reservation.setReservation_time(String.valueOf(timeFormate));
            reservation.setStatus(Reservation.Status.APPORVED);

            //Lưu thông tin đặt bàn
            repositories.save(reservation);

        } catch (Exception exception) {
            throw new RuntimeException("Có lỗi khi đặt bàn" + exception.getMessage());
        }

        return "Đặt bàn thành công.Vui lòng kiểm tra lại!!!";
    }


    //Xem thông tin đặt bàn của khách hàng
    public void viewBookTable() {

    }
}
