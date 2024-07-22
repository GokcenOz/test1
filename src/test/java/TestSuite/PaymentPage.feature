@UItest
@Link1
Feature: Payment Page Scenarios


  Scenario: TEST
    Given go to page
    When ödeme bilgileri sayfasının geldiği doğrulanır
    And "profile-email-input" input alanına "aa@a.com" değeri yazılır.
    And "amount-input" input alanına "20" değeri yazılır.
    And "//div[@class='col _s12 _m12 _l6 pr15 m-mb20']//input[@type='text']" input alanına "testdeneme" değeri yazılır.
    And "//div[@class='col _s12 _m12 _l6 m-mb20']//input[@type='text']" input alanına "5289394722895016" değeri yazılır.
    And "//div[@class='col _s6 _m6 _l3 pr15 m-pr15 m-mb20']//input[@type='text']" input alanına "01/25" değeri yazılır.
    And "//div[@class='col _s6 _m6 _l3 m-mb20 pr15']//input[@type='text']" input alanına "030" değeri yazılır.
    When "//div[@name='contract']//div[@class='simprakit-checkbox-wrapper-check']" tıklanır.
    When "//button[@class='simprakit-button primary full payment-page-submit-button']" tıklanır.
    And 3 sn beklenir.
    And "TAMAM" text değerini içeren elemente tıklanır.
    And 3 sn beklenir.
    Then payment result sayfasının açıldığı onaylanır.
    Then Sayfa kapatılır

  Scenario: Test1
    Given go to page
    When ödeme bilgileri sayfasının geldiği doğrulanır
    And "profile-email-input" input alanına "testdeneme789@gmail.com" değeri yazılır.
    And "profile-phone-input" input alanına "05123456789" değeri yazılır.
    And "profile-name-input" input alanına "deneme789" değeri yazılır.
    And "profile-surname-input" input alanına "test123" değeri yazılır.
    And "profile-identity-number-input" input alanına "12345678998" değeri yazılır.
    And "note-input" input alanına "Bu bir testtir." değeri yazılır.
    And "amount-input" input alanına "15" değeri yazılır.
    And "//div[@class='col _s12 _m12 _l6 pr15 m-mb20']//input[@type='text']" input alanına "testdeneme789" değeri yazılır.
    And "//div[@class='col _s12 _m12 _l6 m-mb20']//input[@type='text']" input alanına "5289394722895016" değeri yazılır.
    And "//div[@class='col _s6 _m6 _l3 pr15 m-pr15 m-mb20']//input[@type='text']" input alanına "01/25" değeri yazılır.
    And "//div[@class='col _s6 _m6 _l3 m-mb20 pr15']//input[@type='text']" input alanına "030" değeri yazılır.
    When "//div[@name='contract']//div[@class='simprakit-checkbox-wrapper-check']" tıklanır.
    When "//button[@class='simprakit-button primary full payment-page-submit-button']" tıklanır.
    And 3 sn beklenir.
    And "TAMAM" text değerini içeren elemente tıklanır.
    And 3 sn beklenir.
    Then payment result sayfasının açıldığı onaylanır.
    Then Sayfa kapatılır

  Scenario: Test2
    Given go to page
    When ödeme bilgileri sayfasının geldiği doğrulanır
    And "profile-email-input" input alanına "isletmemyok@gmail.com" değeri yazılır.
    And "profile-phone-input" input alanına "05123456789" değeri yazılır.
    And "profile-name-input" input alanına "işletmem" değeri yazılır.
    And "profile-surname-input" input alanına "yok" değeri yazılır.
    And "profile-identity-number-input" input alanına "12345678998" değeri yazılır.
    And "//body/div[@id='app']/div[@class='ScrollbarsCustom trackYVisible']/div[@class='ScrollbarsCustom-Wrapper']/div[@class='ScrollbarsCustom-Scroller']/div[@class='ScrollbarsCustom-Content']/div[@class='application-content-main-display active']/div[@class='payment-page']/div[@class='payment-page-top-content']/form[@class='payment-page-form-content ']/div[@class='payment-page-profile-content']/div[4]/div[1]/div[1]/div[2]/div[1]" tıklanır.
    And "Kurumsal" text değerini içeren elemente tıklanır.
    And "tax-office-input" input alanına "testdeneme" değeri yazılır.
    And "tax-number-input" input alanına "123456789" değeri yazılır.
    And "company-name-input" input alanına "İşletmemyok" değeri yazılır.
    And "note-input" input alanına "Bu bir testtir." değeri yazılır.
    And "amount-input" input alanına "87" değeri yazılır.
    And "//div[@class='col _s12 _m12 _l6 pr15 m-mb20']//input[@type='text']" input alanına "işletmemyok" değeri yazılır.
    And "//div[@class='col _s12 _m12 _l6 m-mb20']//input[@type='text']" input alanına "5289394722895016" değeri yazılır.
    And "//div[@class='col _s6 _m6 _l3 pr15 m-pr15 m-mb20']//input[@type='text']" input alanına "01/25" değeri yazılır.
    And "//div[@class='col _s6 _m6 _l3 m-mb20 pr15']//input[@type='text']" input alanına "030" değeri yazılır.
    And "3 Taksit - ₺30,45 / Ay" text değerini içeren elemente tıklanır.
    When "//div[@name='contract']//div[@class='simprakit-checkbox-wrapper-check']" tıklanır.
    When "//button[@class='simprakit-button primary full payment-page-submit-button']" tıklanır.
    And 3 sn beklenir.
    And "TAMAM" text değerini içeren elemente tıklanır.
    And 3 sn beklenir.
    Then payment result sayfasının açıldığı onaylanır.
    Then Sayfa kapatılır

  Scenario: Test3 (Garanti)
    Given go to page
    When ödeme bilgileri sayfasının geldiği doğrulanır
    And "profile-email-input" input alanına "testdeneme99999@gmail.com" değeri yazılır.
    And "profile-phone-input" input alanına "05123456789" değeri yazılır.
    And "profile-name-input" input alanına "deneme789" değeri yazılır.
    And "profile-surname-input" input alanına "test123" değeri yazılır.
    And "profile-identity-number-input" input alanına "12345678998" değeri yazılır.
    And "note-input" input alanına "Bu bir testtir." değeri yazılır.
    And "amount-input" input alanına "15" değeri yazılır.
    And "//div[@class='col _s12 _m12 _l6 pr15 m-mb20']//input[@type='text']" input alanına "testdeneme789" değeri yazılır.
    And "//div[@class='col _s12 _m12 _l6 m-mb20']//input[@type='text']" input alanına "4273142532401017" değeri yazılır.
    And "//div[@class='col _s6 _m6 _l3 pr15 m-pr15 m-mb20']//input[@type='text']" input alanına "06/27" değeri yazılır.
    And "//div[@class='col _s6 _m6 _l3 m-mb20 pr15']//input[@type='text']" input alanına "165" değeri yazılır.
    When "//div[@name='contract']//div[@class='simprakit-checkbox-wrapper-check']" tıklanır.
    When "//button[@class='simprakit-button primary full payment-page-submit-button']" tıklanır.
    And 3 sn beklenir.
    And "TAMAM" text değerini içeren elemente tıklanır.
    And 5 sn beklenir.
    When güvenlik ekranının geldiği doğrulanır.
    And "otp" input alanına "147852" değeri yazılır.
    And "GÖNDER" text değerini içeren elemente tıklanır.
    And 3 sn beklenir.
    Then payment result sayfasının açıldığı onaylanır.
    Then Sayfa kapatılır

  Scenario: Test4 (Garanti)
    Given go to page
    When ödeme bilgileri sayfasının geldiği doğrulanır
    And "profile-email-input" input alanına "testdeneme5989@gmail.com" değeri yazılır.
    And "profile-phone-input" input alanına "05123456789" değeri yazılır.
    And "profile-name-input" input alanına "deneme789" değeri yazılır.
    And "profile-surname-input" input alanına "test123" değeri yazılır.
    And "profile-identity-number-input" input alanına "12345678998" değeri yazılır.
    And "note-input" input alanına "Bu bir testtir." değeri yazılır.
    And "amount-input" input alanına "15" değeri yazılır.
    And "//div[@class='col _s12 _m12 _l6 pr15 m-mb20']//input[@type='text']" input alanına "testdeneme789" değeri yazılır.
    And "//div[@class='col _s12 _m12 _l6 m-mb20']//input[@type='text']" input alanına "5406697543211173" değeri yazılır.
    And "//div[@class='col _s6 _m6 _l3 pr15 m-pr15 m-mb20']//input[@type='text']" input alanına "04/27" değeri yazılır.
    And "//div[@class='col _s6 _m6 _l3 m-mb20 pr15']//input[@type='text']" input alanına "423" değeri yazılır.
    When "//div[@id='is3DChecked']//div[@class='simprakit-checkbox-wrapper-check']" tıklanır.
    When "//div[@name='contract']//div[@class='simprakit-checkbox-wrapper-check']" tıklanır.
    When "//button[@class='simprakit-button primary full payment-page-submit-button']" tıklanır.
    And 3 sn beklenir.
    And "TAMAM" text değerini içeren elemente tıklanır.
    And 5 sn beklenir.
    When güvenlik ekranının geldiği doğrulanır.
    And "otp" input alanına "147852" değeri yazılır.
    When "{string}" tıklanır.
    And 3 sn beklenir.
    Then payment result sayfasının açıldığı onaylanır.
    Then Sayfa kapatılır

  Scenario: Test5 (YapıKredi)
    Given go to page
    When ödeme bilgileri sayfasının geldiği doğrulanır
    And "profile-email-input" input alanına "testdeneme55555555@gmail.com" değeri yazılır.
    And "profile-phone-input" input alanına "05123456789" değeri yazılır.
    And "profile-name-input" input alanına "deneme789" değeri yazılır.
    And "profile-surname-input" input alanına "test123" değeri yazılır.
    And "profile-identity-number-input" input alanına "12345678998" değeri yazılır.
    And "note-input" input alanına "Bu bir testtir." değeri yazılır.
    And "amount-input" input alanına "15" değeri yazılır.
    And "//div[@class='col _s12 _m12 _l6 pr15 m-mb20']//input[@type='text']" input alanına "testdeneme789" değeri yazılır.
    And "//div[@class='col _s12 _m12 _l6 m-mb20']//input[@type='text']" input alanına "5400617030400291" değeri yazılır.
    And "//input[@placeholder='mm/yy']" input alanına "12/25" değeri yazılır.
    And "//div[@class='col _s6 _m6 _l6 m-mb20']//input[@type='text']" input alanına "000" değeri yazılır.
    When "//div[@name='contract']//div[@class='simprakit-checkbox-wrapper-check']" tıklanır.
    When "//button[@class='simprakit-button primary full payment-page-submit-button']" tıklanır.
    And 3 sn beklenir.
    And "TAMAM" text değerini içeren elemente tıklanır.
    And 3 sn beklenir.
    When güvenlik şifresi ekranının geldiği doğrulanır.
    And "smspass" input alanına "34020" değeri yazılır.
    When "//button[normalize-space()='Onay']" tıklanır.
    And 3 sn beklenir.
    Then payment result sayfasının açıldığı onaylanır.
    Then Sayfa kapatılır

  Scenario: Test6 (Yapıkredi)
    Given go to page
    When ödeme bilgileri sayfasının geldiği doğrulanır
    And "profile-email-input" input alanına "aa@a.com" değeri yazılır.
    And "amount-input" input alanına "15" değeri yazılır.
    And "//div[@class='col _s12 _m12 _l6 pr15 m-mb20']//input[@type='text']" input alanına "testdeneme789" değeri yazılır.
    And "//div[@class='col _s12 _m12 _l6 m-mb20']//input[@type='text']" input alanına "5400617030400291" değeri yazılır.
    And "//input[@placeholder='mm/yy']" input alanına "12/25" değeri yazılır.
    And "//div[@class='col _s6 _m6 _l6 m-mb20']//input[@type='text']" input alanına "000" değeri yazılır.
    When "//div[@name='contract']//div[@class='simprakit-checkbox-wrapper-check']" tıklanır.
    When "//button[@class='simprakit-button primary full payment-page-submit-button']" tıklanır.
    And 3 sn beklenir.
    And "TAMAM" text değerini içeren elemente tıklanır.
    And 3 sn beklenir.
    When güvenlik şifresi ekranının geldiği doğrulanır.
    And "smspass" input alanına "34020" değeri yazılır.
    When "//button[normalize-space()='Onay']" tıklanır.
    And 3 sn beklenir.
    Then payment result sayfasının açıldığı onaylanır.
    Then Sayfa kapatılır

  Scenario: TestC&P1
    Given go to CP page.
    And "//input[@id='username']" input alanına "melisamuslu@gmail.com" değeri yazılır.
    And "//input[@id='password']" input alanına "12345678Mm-" değeri yazılır.
    When "kc-login" tıklanır.
    And 3 sn beklenir.
    Then Sayfa kapatılır
    
