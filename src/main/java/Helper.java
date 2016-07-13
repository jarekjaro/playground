final class Helper {

    static String determineDestType(String arg) {
        String destType = arg;
        System.out.println("Destination type is " + destType);
        if (!(destType.equals("queue") || destType.equals("topic"))) {
            System.err.println("Argument must be \"queue\" or " + "\"topic\"");
            System.exit(1);
        }
        return destType;
    }

    static int determineNoOfMsgs(String[] args) {
        int NUM_MSGS;
        if (args.length == 2) {
            NUM_MSGS = new Integer(args[1]);
        } else {
            NUM_MSGS = 1;
        }
        return NUM_MSGS;
    }
}
