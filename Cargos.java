public class Cargos {

    public enum Cargo {
        DESARROLLADOR("Desarrollador"),
        DISENADORA("Diseñadora"),
        GERENTE("Gerente");

        private final String displayName;

        Cargo(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }

        public static Cargo fromString(String s) {
            if (s == null) return null;
            String t = s.trim();
            for (Cargo c : values()) {
                if (c.name().equalsIgnoreCase(t) || c.displayName.equalsIgnoreCase(t)) {
                    return c;
                }
            }
            return null;
        }
    }

}

