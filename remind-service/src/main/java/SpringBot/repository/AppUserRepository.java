package SpringBot.repository;

import SpringBot.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByTelegramUserId(Long id);

    Optional<AppUser> findByTelegramUserIdAndChatId(long telegramUserId, long chatId);

    Optional<AppUser> findByChatId(long chatId);}
