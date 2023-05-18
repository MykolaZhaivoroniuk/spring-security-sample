// Issue
@Controller
public class MyController {
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login() {
        Map<String, Object> responseMap = new HashMap<>();
        String secret = "my-super-duper-secret-key";

        User user = new User(123, "John Doe");
        String token = JwtUtil.sign(user, secret);

        responseMap.put("token", token);
        return ResponseEntity.ok(responseMap);
    }
}

// Remediation
@Controller
public class MyController {

    @Autowired
    private Environment env;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login() {
        Map<String, Object> responseMap = new HashMap<>();

        User user = new User(123, "John Doe");
        String token = JwtUtil.sign(user, env.getProperty("spring.secret_key"));

        responseMap.put("token", token);
        return ResponseEntity.ok(responseMap);
    }
}