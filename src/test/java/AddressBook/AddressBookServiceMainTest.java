package AddressBook;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


import static AddressBook.AddressBookServiceMain.IOService.DB_IO;
import static org.junit.jupiter.api.Assertions.*;

class AddressBookServiceMainTest
{
    @Test
    public void givenAddressBookInDB_WhenRetrieved_ShouldMatchThePeopleCount() {
        AddressBookServiceMain addressBookService = new AddressBookServiceMain();
        List<AddressBookData> addressBookDataList = addressBookService.readAddressBookData(DB_IO);
        assertEquals(4,addressBookDataList.size());
    }
    @Test
    public void givenNewPhoneNumber_ShouldUpdateTheRecordAndSyncWithDataBase() throws AddressBookException {
        AddressBookServiceMain addressBookService = new AddressBookServiceMain();
        addressBookService.readAddressBookData(DB_IO);
        addressBookService.updateRecord("kavya", "9910936991");
        boolean result = addressBookService.checkRecordSyncWithDB("kavya");
        assertTrue(result);
    }
    @Test
    public void givenDate_ShouldRetrieveTheAddressBookRecord_BasedOnTheParticularRange() {
        AddressBookServiceMain addressBookService = new AddressBookServiceMain();
        addressBookService.readAddressBookData(DB_IO);
        LocalDate startDate = LocalDate.of(2018, 01, 01);
        LocalDate endDate = LocalDate.now();
        List< AddressBookData> employeePayrollData= addressBookService.readEmployeePayrollForDateRange(DB_IO, startDate, endDate);
        assertEquals(2,employeePayrollData.size());
    }
    @Test
    public void givenCity_ShouldRetrieveTheNumberOfContacts_BasedOnCity() {
        AddressBookServiceMain addressBookService = new AddressBookServiceMain();
        addressBookService.readAddressBookData(DB_IO);
        Map<String, Double> contactsByCity = addressBookService.contactsByCity(DB_IO);
        System.out.println(contactsByCity.containsKey("KARANATAKA")+" "+contactsByCity.containsValue(2.0));
        assertTrue(contactsByCity.containsKey("KARANATAKA") && contactsByCity.containsValue(2.0));
    }

    @Test
    public void givenCity_ShouldRetrieveTheNumberOfContacts_BasedOnState() {
        AddressBookServiceMain addressBookService = new AddressBookServiceMain();
        addressBookService.readAddressBookData(DB_IO);
        Map<String, Double> contactsByState = addressBookService.contactsByState(DB_IO);
        System.out.println(contactsByState.containsKey("Davangere")+" "+contactsByState.containsValue(2.0));
        assertTrue(contactsByState.containsKey("Davangere") &&
                contactsByState.containsValue(2.0));
    }
    @Test
    public void givenNewContact_ShouldAddIntoTheAddressBookDataBase() {
        AddressBookServiceMain addressBookService = new AddressBookServiceMain();
        addressBookService.readAddressBookData(DB_IO);
        addressBookService.addNewContact("Kynat","Zehra","Friend",
                "9999999989","Noida","Delhi","121004","kynat@gmail.com",LocalDate.now());
        boolean result = addressBookService.checkRecordSyncWithDB("Kynat");
        assertTrue(result);
    }
}