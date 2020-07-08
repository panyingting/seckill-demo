package reinty.study.seckill.autoconfig.kafka;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@ConditionalOnProperty(prefix = "kafka.consumer")
public class ConsumerConfiguration {
    private String bootstrapServers;

    private String groupId;

    private boolean autoCommit;

    private int commitIntervalMs;

    private String offsetReset;

    private int sessionTimeoutMs;

    private  String deserializer;

    public String getBootstrapServers() {
        return bootstrapServers;
    }

    public void setBootstrapServers(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public boolean isAutoCommit() {
        return autoCommit;
    }

    public void setAutoCommit(boolean autoCommit) {
        this.autoCommit = autoCommit;
    }

    public int getCommitIntervalMs() {
        return commitIntervalMs;
    }

    public void setCommitIntervalMs(int commitIntervalMs) {
        this.commitIntervalMs = commitIntervalMs;
    }

    public String getOffsetReset() {
        return offsetReset;
    }

    public void setOffsetReset(String offsetReset) {
        this.offsetReset = offsetReset;
    }

    public int getSessionTimeoutMs() {
        return sessionTimeoutMs;
    }

    public void setSessionTimeoutMs(int sessionTimeoutMs) {
        this.sessionTimeoutMs = sessionTimeoutMs;
    }

    public String getDeserializer() {
        return deserializer;
    }

    public void setDeserializer(String deserializer) {
        this.deserializer = deserializer;
    }

    @Override
    public String toString() {
        return "ConsumerConfiguration{" +
                "bootstrapServers='" + bootstrapServers + '\'' +
                ", groupId='" + groupId + '\'' +
                ", autoCommit=" + autoCommit +
                ", commitIntervalMs=" + commitIntervalMs +
                ", offsetReset='" + offsetReset + '\'' +
                ", sessionTimeoutMs=" + sessionTimeoutMs +
                ", deserializer='" + deserializer + '\'' +
                '}';
    }
}
