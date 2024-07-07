import java.util.Objects;

public class HamiltonianReport {

    public enum Status {
        VALID, NULL_INPUT, INVALID_LENGTH, INVALID_CYCLE, INVALID_NODE
    }

    private final Status status; // non-nullable
    private final String badNode; // nullable

    /**
     * Constructs an immutable HamiltonianReport, a data class, using the given parameters
     *
     * @param status    One of VALID, NULL_INPUT, INVALID_LENGTH, INVALID_CYCLE, or INVALID_NODE
     * @param badNode   If status is INVALID_NODE, badNode is the String value of the invalid node;
     *                  if status is anything else, set to null
     */
    public HamiltonianReport(Status status, String badNode) {
        if (status == null)
            throw new IllegalArgumentException("null enum supplied as argument to HamiltonianReport");
        this.status = status;
        this.badNode = badNode;
    }

    public Status getStatus() {
        return status;
    }

    public String getBadNode() {
        return badNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HamiltonianReport that = (HamiltonianReport) o;
        return status == that.status && Objects.equals(badNode, that.badNode);
    }

    @Override
    public String toString() {
        return "HamiltonianReport{status=" + status +
        ", badNode=" + badNode + "}";
    }
}
