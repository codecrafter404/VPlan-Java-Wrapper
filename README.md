# Archived
Our school switched their VPlan system...

# VPlan-Java-Wrapper
This is a wrapper for the API from gykl.de

# Usage
## Get Week
```
new VPlanAPI("host.net", "password", isMD5Hash).getWeek(
                List.of(new Date("YYYY-MM-DD", timestamp)),
                "class"
        );
```
## Check Password
```
new VPlanAPI("host.net", "password", isMD5Hash).checkPassword("lol");
```

No warrenty, this is an unoffical Project!
