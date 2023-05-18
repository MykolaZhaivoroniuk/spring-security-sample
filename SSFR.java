// Issue
@Controller
public class MyController {
    @RequestMapping(value = "/api/data", method = RequestMethod.GET)
    public ResponseEntity<?> api_data(@RequestParam("url") String url) {
        Map<String, Object> responseMap = new HashMap<>();
        String response = proxyService.fetchUrl(url);
        responseMap.put("data", response);
        return ResponseEntity.ok(responseMap);
    }
}

// Remediation
@Controller
public class MyController {
    @RequestMapping(value = "/api/data", method = RequestMethod.GET)
    public ResponseEntity<?> api_data(@RequestParam("url") String url) {
        Map<String, Object> responseMap = new HashMap<>();
        List<String> allowedUrls = Arrays.asList("https://example.com/countries.json", "https://example.com/states.json");

        if(url == "" || !url.startsWith("https://")) {
            responseMap.put("error", "Invalid URL scheme");
            return ResponseEntity.badRequest().body(responseMap);
        } else if(!allowedUrls.contains(url)) {
            responseMap.put("error", "Not allowed");
            return ResponseEntity.badRequest().body(responseMap);
        }

        String response = proxyService.fetchUrl(url);
        responseMap.put("data", response);
        return ResponseEntity.ok(responseMap);
    }
}
