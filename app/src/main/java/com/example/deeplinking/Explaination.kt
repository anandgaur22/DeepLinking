package com.example.deeplinking

/*


What is Deep Linking?
Deep Linking means that when a specific URL is opened, it directly navigates to a
particular screen within the app.

For example, if you share a product link with a friend, and clicking that link opens
the product page in the app, this is an example of deep linking.

Types of Deep Linking:
Traditional Deep Linking
Deferred Deep Linking
Dynamic Deep Linking (Firebase Dynamic Links)

1. Traditional Deep Linking
- Uses a predefined URL scheme that works only if the app is already installed.
- If the app is not installed, the link will not work.

Example:

myapp://product/123 → This will only work if the app is installed.

Limitations:

Does not work if the app is not installed.
Needs custom URL schemes, which are not universal.

2. Deferred Deep Linking
Works even if the app is not installed at the time of clicking the link.
If the app is not installed, the user is first redirected to the Play Store to install the app.
After installation, it opens the specific screen the user was supposed to access.

Use Case:
* Used in marketing campaigns, referral programs, and user acquisition strategies.
* Example: If a referral invite link is sent to a new user, after installation, the app should open the referral offer page directly.


3. Dynamic Deep Linking

Firebase Dynamic Links support both traditional and deferred deep linking.
Works seamlessly across Android, iOS, and Web.
Automatically detects whether the app is installed:
If installed → Opens the app directly on the correct screen.
If not installed → Redirects to the Play Store → After installation, opens the correct screen.

Advantages:
Works across multiple platforms (Android, iOS, Web).
Helps in seamless user onboarding.
Used in advertising, social media sharing, and app promotions.

https://myapp.page.link/product123


* */