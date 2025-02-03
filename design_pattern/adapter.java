// step 1: define the target interface (iPhone charging)
interface LightningCharger {
    void chargeWithLightning();
}

// step 2: implement adaptee (type C Charger)
class USBCharger {
    public void chargeWithUSB() {
        System.out.println("charging using type C");
    }
}

// step 3: create Adapter that convert type C to lightning
class LightningToUSBAdapter implements LightningCharger {
    private USBCharger usbCharger;

    public LightningToUSBAdapter(USBCharger usbCharger) {
        this.usbCharger = usbCharger;
    }

    @Override
    public void chargeWithLightning() {
        System.out.println("adapter converting Lightning to type C...");
        usbCharger.chargeWithUSB();
    }
}

// step 4: user code (iPhone)
public class adapter{
    public static void main(String[] args) {
        // we only have a type C charger
        USBCharger usbCharger = new USBCharger();

        // use adapter to allow iPhone to use type C charger
        LightningCharger adapter = new LightningToUSBAdapter(usbCharger);
        
        System.out.println("charging iPhone with type C using adapter:");
        adapter.chargeWithLightning(); // this calls type C charger through adapter
    }
}
