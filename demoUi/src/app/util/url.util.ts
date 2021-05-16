import {environment} from "../../environments/environment";

export function decorateUrl(url: string): string {
  const result = (environment.host + (environment.port ? ":" + environment.port : "")) + url;
  console.log("url=" + result);
  return result;
}
