package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class V3_0_0__Update_Book extends BaseJavaMigration {

    private Context context;

    @Override
    public void migrate(Context context) {
        this.context = context;
        selectBookById().ifPresent(this::updateBookTitle);
    }

    private void updateBookTitle(Long id) {
        try (Statement update = context.getConnection().createStatement()) {
            update.execute("UPDATE book SET title='MyFirstBookUpdated' WHERE id=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Optional<Long> selectBookById() {
        try (Statement select = context.getConnection().createStatement()) {
            ResultSet rows = select.executeQuery("SELECT id FROM book ORDER BY id");
            if (rows.next()) {
                return Optional.of(rows.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
