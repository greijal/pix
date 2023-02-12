package com.itau.pix.dto.response;

import java.io.Serializable;

public class CreateResponseDTO implements Serializable {
        private  Long id;

        public CreateResponseDTO(Long id) {
                this.id = id;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }
}
