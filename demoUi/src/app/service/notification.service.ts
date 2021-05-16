import {Inject, Injectable} from '@angular/core';
import {MessageService} from 'primeng/api';
import {MessageSeverity, MessageSummary} from "../domain/message";


@Injectable()
export class NotificationService {
  loading: boolean;

  constructor(@Inject(MessageService) private readonly messageService : MessageService) {
    this.loading = false;
    this.clear();
  }

  success(detail: string, summary?: string): void {
    this.loading = false;
    this.clear();
    this.messageService.add({
      severity:MessageSeverity.SUCCESS,
      summary:summary ? summary : MessageSummary.SUCCESS,
      detail: `${detail}`
    });

  }

  info(detail: string, summary?: string): void {
    this.loading = false;
    this.messageService.add({
      severity:MessageSeverity.INFO,
      summary:summary ? summary : MessageSummary.INFO,
      detail: `${detail}`
    });
  }

  warning(detail: string, summary?: string): void {
    this.loading = false;
    this.messageService.add({
      severity:MessageSeverity.WARN,
      summary:summary ? summary : MessageSummary.WARN,
      detail: `${detail}`
    });
  }

  error(detail: string, summary?: string): void {
    this.loading = false;
    this.messageService.add({
      severity: MessageSeverity.ERROR,
      summary: summary ? summary : MessageSummary.ERROR,
      detail: `${detail}`
    });
  }

  clear() {
    this.messageService.clear();
  }
}
