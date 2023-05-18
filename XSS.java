// Issue
@Controller
public class MyController {
    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public String redirect(@RequestParam("redirect") String redirect) {
        return "redirect:" + redirect;
    }
}

// Remediation
@Controller
public class MyController {
    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public String redirect(@RequestParam("redirect") String redirect) {
        if(redirect.startsWith("https://")) {
            return "redirect:" + redirect;
        } else {
            return "redirect:/";
        }
    }
}