import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingResource {

    @PostMapping
    public ResponseEntity<String> createBooking(@RequestBody String bookingDetails) {
        // Logic to create booking
        return ResponseEntity.ok("Booking created.");
    }

    @GetMapping
    public ResponseEntity<String> getAllBookings() {
        // Logic to get all bookings
        return ResponseEntity.ok("All bookings.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getBookingById(@PathVariable String id) {
        // Logic to get booking by ID
        return ResponseEntity.ok("Booking with ID " + id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBooking(@PathVariable String id, @RequestBody String updatedDetails) {
        // Logic to update booking
        return ResponseEntity.ok("Booking with ID " + id + " updated.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable String id) {
        // Logic to delete booking
        return ResponseEntity.ok("Booking with ID " + id + " deleted.");
    }
}