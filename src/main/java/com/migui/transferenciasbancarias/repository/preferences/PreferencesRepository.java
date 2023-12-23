package com.migui.transferenciasbancarias.repository.preferences;

import com.migui.transferenciasbancarias.model.preferences.Preferences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferencesRepository extends JpaRepository<Preferences, Integer> {
}
