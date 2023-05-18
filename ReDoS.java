// Issue
@Controller
public class MyController {
    @RequestMapping(value = "/validateEmail", method = RequestMethod.POST)
    public ResponseEntity<?> validateEmail(@RequestParam("email") String email) {
        Map<String, Object> responseMap = new HashMap<>();
        String pattern = "^([a-zA-Z0-9])(([\-.]|[_]+)?([a-zA-Z0-9]+))*(@){1}[a-z0-9]+[.]{1}(([a-z]{2,3})|([a-z]{2,3}[.]{1}[a-z]{2,3}))$";
        
        if(email == null || !email.matches(pattern)) {
            return ResponseEntity.badRequest().body("Invalid email");
        }

        responseMap.put("valid", true);
        return ResponseEntity.ok(responseMap);
    }
}

// Remediation
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Email;
import java.util.Set;

@Controller
public class MyController {
    @RequestMapping(value = "/validateEmail", method = RequestMethod.POST)
    public ResponseEntity<?> validateEmail(@RequestParam("email") String email) {
        Map<String, Object> responseMap = new HashMap<>();
        
        if(!EmailValidator.validateEmail(email)) {
            return ResponseEntity.badRequest().body("Invalid email");
        }

        responseMap.put("valid", true);
        return ResponseEntity.ok(responseMap);
    }
}

// Use validation library
public class EmailValidator {
    public static boolean validateEmail(String email) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        ContactForm form = new ContactForm();
        form.setEmail(email);

        Set<ConstraintViolation<ContactForm>> violations = validator.validate(form);

        return violations.isEmpty();
    }

    private static class ContactForm {
        @Email
        private String email;

        public void setEmail(String email) {
            this.email = email;
        }
    }
}