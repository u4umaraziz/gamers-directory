/*
 * Copyright 2017-2021 Commencis Limited. All Rights Reserved.
 *
 * Save to the extent permitted by law, you may not use, copy, modify,
 * distribute or create derivative works of this material or any part
 * of it without the prior written consent of Commencis Limited.
 * Any reproduction of this material must contain this notice.
 */

package com.bestsellers.assignment.gamersdirectory.exception;

import com.bestsellers.assignment.gamersdirectory.constants.ErrorConstant;
import org.springframework.http.HttpStatus;

public class NoPlayerFoundException extends BaseException {
    public NoPlayerFoundException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, ErrorConstant.NO_GAMER_FOUND_ERROR_CODE);
    }
}
