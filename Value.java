package bvclient.project;

public class Value implements Comparable<Value> {

        public Boolean selected;
        public String value;

        public Value(Boolean selected, String value) {
            this.selected = selected;
            this.value = value;
        }

        @Override
        public int compareTo(Value v) {
            return this.value.compareTo(v.value);
        }

        @Override
        public boolean equals(Object v) {
            return v instanceof Value && this.value.equals(((Value) v).value);
        }

        @Override
        public int hashCode() {
            return this.value.hashCode();
        }
    }