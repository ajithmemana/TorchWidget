TorchWidget
===========

A widget that can be used to swithc On and OFF  the LED Flash of your phone



Add the following lines to ingore devices without led flash

private boolean doesCameraSupportFlash(Parameters params) {
if (params.getFlashMode() == null) {
           return false;
       }

       List<String> supportedFlashModes = params.getSupportedFlashModes();
       if (supportedFlashModes == null || supportedFlashModes.isEmpty() || supportedFlashModes.size() == 1 && supportedFlashModes.get(0).equals(Camera.Parameters.FLASH_MODE_OFF)) {
           return false;
       }

       return true;
}
