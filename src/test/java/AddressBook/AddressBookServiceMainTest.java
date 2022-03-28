package AddressBook;



import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static AddressBook.AddressBookServiceMain.IOService.DB_IO;
import static org.junit.jupiter.api.Assertions.*;


public class AddressBookServiceMainTest {
    @Test
    public void givenAddressBookInDB_WhenRetrieved_ShouldMatchThePeopleCount() {
        AddressBookServiceMain addressBookService = new AddressBookServiceMain();
        List<AddressBookData> addressBookDataList = addressBookService.readAddressBookData(DB_IO);
        assertEquals(3,addressBookDataList.size());
    }

    @Test
    public void givenNewPhoneNumber_ShouldUpdateTheRecordAndSyncWithDataBase() throws AddressBookException {
        AddressBookServiceMain addressBookService = new AddressBookServiceMain();
        addressBookService.readAddressBookData(DB_IO);
        addressBookService.updateRecord("Nayana", "8008008008");
        boolean result = addressBookService.checkRecordSyncWithDB("Nayana");
      assertFalse(result);
    }

    @Test
    public void givenDate_ShouldRetrieveTheAddressBookRecord_BasedOnThePerticularRange() {
        AddressBookServiceMain addressBookService = new AddressBookServiceMain();
        addressBookService.readAddressBookData(DB_IO);
        LocalDate startDate = LocalDate.of(2018, 01, 01);
        LocalDate endDate = LocalDate.now();
        List< AddressBookData> employeePayrollData=
                addressBookService.readEmployeePayrollForDateRange(DB_IO, startDate, endDate);
        assertEquals(2,employeePayrollData.size());
    }
}