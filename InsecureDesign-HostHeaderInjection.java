// Issue
@Controller
public class MyController {
    @RequestMapping(value = "/generate-pwd-reset-url", method = RequestMethod.POST)
    public ResponseEntity<?> generatePwdResetUrl(@genPwdResetToken("host") String host, @RequestParam("email") String email) {
        Map<String, Object> responseMap = new HashMap<>();

        Customer customer = customerModel.findCustomerByEmail(email);
        String resetToken = genPwdResetToken(customer.getId());
        String resetPwdUrl = host + "/passwordReset?token=" + resetToken + "&id=" + customer.getId;

        responseMap.put("resetPwdUrl", resetPwdUrl);
        return ResponseEntity.ok(responseMap);
    }
}

// Remediation
@Controller
public class MyController {
    @Autowired
    private Environment env;

    @RequestMapping(value = "/generate-pwd-reset-url", method = RequestMethod.POST)
    public ResponseEntity<?> generatePwdResetUrl(@RequestParam("email") email) {
        Map<String, Object> responseMap = new HashMap<>();

        Customer customer = customerModel.findCustomerByEmail(email);
        String resetToken = genPwdResetToken(customer.getId());
        String resetPwdUrl = env.getProperty("spring.host") + "/passwordReset?token=" + resetToken + "&id=" + customer.getId;

        responseMap.put("resetPwdUrl", resetPwdUrl);
        return ResponseEntity.ok(responseMap);
    }
}