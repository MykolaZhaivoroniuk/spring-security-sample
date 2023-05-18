// Issue
@Controller
public class MyController {
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> signup(@RequestBody User user) {
        Map<String, Object> responseMap = new HashMap<>();

        String username = user.getUsername();
        String email = user.getEmail();
        String password = user.getPassword();

        List<User> findResult = userModel.findUserByname(username);

        if(findResult.count() == 0) {
            userModel.insert(username, email, password);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 409
        }
    }
}

// Remediation
@Controller
public class MyController {
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> signup(@RequestBody User user) {
        Map<String, Object> responseMap = new HashMap<>();

        String username = user.getUsername();
        String email = user.getEmail();
        String password = user.getPassword();

        List<User> findResult = userModel.findUserByname(username);

        if(findResult.count() == 0) {
            userModel.insert(username, email, encryptedPwd(password)); // Encrypt password
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 409
        }
    }
}