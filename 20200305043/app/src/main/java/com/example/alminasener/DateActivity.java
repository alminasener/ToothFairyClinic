package com.example.alminasener;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DateActivity extends AppCompatActivity {

    /* Takvimde bir tarih seçildiğinde, bu tarih
    "selectedDate"
     değişkenine atanır ve daha sonra bu tarih, kullanıcının o tarihe randevu almasını
    veya bu tarihle ilgili başka bir işlem yapmasını sağlamak için kullanılır. */
    private Date selectedDate;
    private Map<String, ArrayList<String>> bookedAppointmentsByDate = new HashMap<>(); // Her gün için rezervasyonlar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        // XML dosyasındaki elemanlar ile Java kodu arasındaki bağlantıları kurduk.
        CalendarView calendarView = findViewById(R.id.calendarView);
        Button[] hourButtons = new Button[]{
                findViewById(R.id.button1),
                findViewById(R.id.button2),
                findViewById(R.id.button3),
                findViewById(R.id.button4),
                findViewById(R.id.button5),
                findViewById(R.id.button6),
                findViewById(R.id.button7),
                findViewById(R.id.button8),
                findViewById(R.id.button9)
        };

        // Takvim görüntüsündeki tarih değiştiğinde yapılacak işlemler tanımladık.
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            selectedDate = getDate(year, month, dayOfMonth);
            if (selectedDate != null) {
                if (selectedDate.after(Calendar.getInstance().getTime())) {
                    // Geçerli bir tarih, burada herhangi bir işlem yapmıyoruz.
                } else {
                    // Geçersiz tarih, uyarı gösterdik.
                    Toast.makeText(this, "Invalid date. Please select a future date.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Saat butonlarına tıklandığında yapılacak işlemler tanımladık.
        for (Button button : hourButtons) {
            button.setOnClickListener(v -> {
                // Saat butonu tıklandığında yapılacak işlemler;
                if (selectedDate != null && selectedDate.after(Calendar.getInstance().getTime())) {
                    String selectedHour = ((Button) v).getText().toString();
                    if (!isAppointmentBooked(selectedDate, selectedHour)) {
                        // Saat daha önce rezerve edilmediyse randevu oluşturuyoruz.
                        bookAppointment(selectedDate, selectedHour);
                        showAppointmentMessage(selectedHour);
                    } else {
                        // Saat zaten rezerve edildiyse uyarı gösteriyoruz.
                        Toast.makeText(this, "This hour is already booked. Please select another hour.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Please select a valid date first.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private Date getDate(int year, int month, int dayOfMonth) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            return sdf.parse(year + "-" + (month + 1) + "-" + dayOfMonth);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Randevu oluşturulduğunda gösterilecek mesajı hazırlayan metod;
    private void showAppointmentMessage(String selection) {
        String message = "Your appointment has been created for " + formatDate(selectedDate) + " at " + selection;
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return sdf.format(date);
    }

    // Tarihe ve saate göre bir randevunun daha önce rezerve edilip edilmediğini kontrol eden metod;
    private boolean isAppointmentBooked(Date date, String selectedHour) {
        String formattedDate = formatDate(date);

        if (bookedAppointmentsByDate.containsKey(formattedDate)) {
            ArrayList<String> bookedHours = bookedAppointmentsByDate.get(formattedDate);
            return bookedHours != null && bookedHours.contains(selectedHour);
        }

        return false;
    }

    private void bookAppointment(Date date, String selectedHour) {
        String formattedDate = formatDate(date);

        if (bookedAppointmentsByDate.containsKey(formattedDate)) {
            bookedAppointmentsByDate.get(formattedDate).add(selectedHour);
        } else {
            ArrayList<String> hours = new ArrayList<>();
            hours.add(selectedHour);
            bookedAppointmentsByDate.put(formattedDate, hours);
        }
    }
}