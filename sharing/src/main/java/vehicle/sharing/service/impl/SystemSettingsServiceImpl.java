package vehicle.sharing.service.impl;

// SystemSettingsServiceImpl.java

import vehicle.sharing.model.SystemSettings;
import vehicle.sharing.repository.SystemSettingsRepository;
import vehicle.sharing.service.SystemSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SystemSettingsServiceImpl implements SystemSettingsService {

    @Autowired
    private SystemSettingsRepository systemSettingsRepository;

    @Override
    public SystemSettings saveSetting(SystemSettings setting) {
        return systemSettingsRepository.save(setting);
    }

    @Override
    public Optional<SystemSettings> getSettingByKey(String key) {
        return systemSettingsRepository.findBySettingKey(key);
    }

    @Override
    public List<SystemSettings> getAllSettings() {
        return systemSettingsRepository.findAll();
    }

    @Override
    public void deleteSetting(Long id) {
        systemSettingsRepository.deleteById(id);
    }
}
