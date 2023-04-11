package org.zerogravitysolutions.email;

import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl {

    private final EmailRepository emailRepository;

    public EmailServiceImpl(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }
}
