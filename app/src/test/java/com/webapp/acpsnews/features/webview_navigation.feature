Feature: WebView Navigation

  Scenario: Open WebView and navigate
    Given the app is launched
    When I open the News tab
    Then the WebView should load the News URL