import java.util.UUID;

public class UUIDIdService implements IdService {

    @Override
    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
