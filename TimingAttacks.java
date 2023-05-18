// Issue
public boolean checkToken(userSupplied) {
    Account accountData = account.retrieveToken(userSupplied);

    if(accountData != null) {
        if(accountData.getService().getToken() == user.getService().getToken()) {
            return true;
        }
    }

    return false;
}

// Remediation
public boolean checkToken(userSupplied) {
        Account accountData = account.retrieveToken(userSupplied);

    if(accountData != null) {
        if(isEqual(accountData.getService().getToken(), user.getService().getToken())) {
            return true;
        }
    }

    return false;
}

private boolean isEqual(String s1, String s2) {
    // Use a constant-time comparison method
    if (s1.length() != s2.length()) {
        return false;
    }

    int result = 0;
    for (int i = 0; i < s1.length(); i++) {
        result |= s1.charAt(i) ^ s2.charAt(i);
    }
    
    return result == 0;
}