// Issue
@Controller
public class MyController {
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<?> users(@RequestBody User user) {
        Map<String, Object> responseMap = new HashMap<>();
        //assume the user is authenticated
        //...

        User user = userModl.findUser(user); // When convert string to array.
        if(user == null) {
            return ResponseEntity.badRequest().body("There was an error finding user");
        } else {
            responseMap.put("data", user.toString());
        }

        return ResponseEntity.ok(responseMap);
    }
}

// Remediation
@Controller
public class MyController {
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<?> users(@RequestBody User user) {
        Map<String, Object> responseMap = new HashMap<>();
        //assume the user is authenticated
        //...

        // Validate username and password values

        if(!isValidUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("Invalid username");
        }

        User user = userModl.findUserByname(user.getUsername()); // Only filter by string.
        if(user == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There was an error finding user"); // 500
        } else {
            responseMap.put("data", user.toString());
        }

        return ResponseEntity.ok(responseMap);
    }

    private boolean isValidUsername(String username) {
        if(username == null || username == "") return false;

        // Validate other options...

        return true;
    }
}