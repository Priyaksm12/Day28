package AddressBook;


import org.junit.jupiter.api.Test;
import java.util.List;

import static AddressBook.AddressBookServiceMain.IOService.DB_IO;
import static org.junit.jupiter.api.Assertions.*;

class AddressBookServiceMainTest
{
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
        addressBookService.updateRecord("NAYANA", "8008008008 ");
        boolean result = addressBookService.checkRecordSyncWithDB("NAYANA");
        assertTrue(result);
    }
}