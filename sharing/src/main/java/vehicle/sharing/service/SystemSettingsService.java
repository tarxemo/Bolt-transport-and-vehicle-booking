package vehicle.sharing.service;

// SystemSettingsService.java

import vehicle.sharing.model.SystemSettings;
import java.util.List;
import java.util.Optional;

public interface SystemSettingsService {
    SystemSettings saveSetting(SystemSettings setting);
    Optional<SystemSettings> getSettingByKey(String key);
    List<SystemSettings> getAllSettings();
    void deleteSetting(Long id);
}
