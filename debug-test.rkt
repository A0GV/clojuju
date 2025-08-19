#lang racket

;; Simple test for tokenization
(require racket/string)

;; Test simple pattern matching
(define rg-system (list "r-system" #rx"^system:"))
(define simple-dict (list rg-system))

(define test-input "system: cup")

;; Simple all-matches function
(define (all-matches input-text rg-dict)
  (printf "Input: ~a~n" input-text)
  (filter (lambda (match-result) (not (equal? match-result #f)))
          (map (lambda (regex-item)
                 (let* ([token-name (first regex-item)]
                        [rg-pattern (second regex-item)]
                        [matched-result (regexp-match-positions rg-pattern input-text)])
                   (printf "Testing pattern ~a: ~a~n" token-name matched-result)
                   
                   (if matched-result
                       (if (equal? 0 (caar matched-result))
                           (let ([matched-txt (regexp-match rg-pattern input-text)])
                             (printf "Match found: ~a~n" matched-txt)
                             (list token-name (first matched-txt)))
                           #f)
                       #f)))
               rg-dict)))

(define result (all-matches test-input simple-dict))
(printf "Final result: ~a~n" result)